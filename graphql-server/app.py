from flask import Flask, request, jsonify
from ariadne import QueryType, make_executable_schema, graphql_sync
from ariadne.explorer import ExplorerGraphiQL  
from db import SessionLocal
from models import Donacion
from datetime import datetime

# --- 1. Definimos el esquema (SDL) ---
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

# --- 2. Definimos la lógica (resolvers) ---
query = QueryType()

@query.field("informeDonaciones")
def resolve_informe_donaciones(_, info, categoria=None, fechaInicio=None, fechaFin=None, eliminado=None):
    donaciones = [
        {"categoria": "Ropa", "cantidad": 10, "eliminado": False, "fecha_alta": "2025-01-03"},
        {"categoria": "Ropa", "cantidad": 20, "eliminado": False, "fecha_alta": "2025-02-05"},
        {"categoria": "Alimentos", "cantidad": 5, "eliminado": True, "fecha_alta": "2025-03-10"},
        {"categoria": "Alimentos", "cantidad": 15, "eliminado": False, "fecha_alta": "2025-01-20"},
    ]

    from datetime import datetime

    def to_date(s):
        if not s:
            return None
        return datetime.fromisoformat(s)

    d_start = to_date(fechaInicio)
    d_end = to_date(fechaFin)

    filtered = []
    for d in donaciones:
        if categoria and d["categoria"] != categoria:
            continue
        if eliminado == "si" and not d["eliminado"]:
            continue
        if eliminado == "no" and d["eliminado"]:
            continue
        if d_start or d_end:
            fecha = datetime.fromisoformat(d["fecha_alta"])
            if d_start and fecha < d_start:
                continue
            if d_end and fecha > d_end:
                continue
        filtered.append(d)

    resultado = {}
    for d in filtered:
        key = (d["categoria"], d["eliminado"])
        resultado[key] = resultado.get(key, 0) + d["cantidad"]

    return [
        {"categoria": k[0], "eliminado": k[1], "totalCantidad": v}
        for k, v in resultado.items()
    ]

# --- 3. Creamos el esquema ejecutable ---
schema = make_executable_schema(type_defs, query)

# --- 4. Servidor Flask ---
app = Flask(__name__)
explorer_html = ExplorerGraphiQL().html(None)  # interfaz web moderna

@app.route("/graphql", methods=["GET"])
def graphql_playground():
    return explorer_html, 200  # 👈 cambia esta línea

@app.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code

if __name__ == "__main__":
    app.run(debug=True, port=5000)
