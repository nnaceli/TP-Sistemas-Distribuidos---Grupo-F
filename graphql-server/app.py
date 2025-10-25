from flask import Flask, request, jsonify
from ariadne import QueryType, make_executable_schema, graphql_sync
from ariadne.explorer import ExplorerGraphiQL  
from db import SessionLocal
from models import Donacion
from datetime import datetime

type_defs = """
    type ResultadoDonaciones {
        categoria: String
        eliminado: Boolean
        totalCantidad: Int
    }

    type Query {
        informeDonaciones(
            categoria: String,
            fechaInicio: String,
            fechaFin: String,
            eliminado: String
        ): [ResultadoDonaciones]
    }
"""

query = QueryType()

@query.field("informeDonaciones")
def resolve_informe_donaciones(_, info, categoria=None, fechaInicio=None, fechaFin=None, eliminado=None):
    db = SessionLocal()  # abrir sesión con la base
    q = db.query(Donacion)

    # Aplicar filtros según los parámetros del GraphQL
    if categoria:
        q = q.filter(Donacion.categoria == categoria)
    if eliminado == "si":
        q = q.filter(Donacion.eliminado == True)
    if eliminado == "no":
        q = q.filter(Donacion.eliminado == False)
    if fechaInicio:
        q = q.filter(Donacion.fecha_alta >= datetime.fromisoformat(fechaInicio))
    if fechaFin:
        q = q.filter(Donacion.fecha_alta <= datetime.fromisoformat(fechaFin))

    donaciones = q.all()

    resultado = {}
    for d in donaciones:
        key = (d.categoria, d.eliminado)
        resultado[key] = resultado.get(key, 0) + d.cantidad

    db.close()

    return [
        {"categoria": k[0], "eliminado": k[1], "totalCantidad": v}
        for k, v in resultado.items()
    ]


schema = make_executable_schema(type_defs, query)

app = Flask(__name__)
explorer_html = ExplorerGraphiQL().html(None)  

@app.route("/graphql", methods=["GET"])
def graphql_playground():
    return explorer_html, 200  

@app.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code

if __name__ == "__main__":
    app.run(debug=True, port=5000)
