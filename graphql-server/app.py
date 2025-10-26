from flask import Flask, request, jsonify
from ariadne import QueryType, MutationType, make_executable_schema, graphql_sync
from ariadne.explorer import ExplorerGraphiQL
from db import SessionLocal
from models import Donacion, FiltroDonacion
from datetime import datetime

# --- 1. Definimos el esquema (SDL) ---
type_defs = """
    type ResultadoDonaciones {
        categoria: String
        eliminado: Boolean
        totalCantidad: Int
    }

    type FiltroDonacion {
        id: ID
        nombre: String
        categoria: String
        fechaInicio: String
        fechaFin: String
        eliminado: String
    }

    input FiltroDonacionInput {
        nombre: String!
        categoria: String
        fechaInicio: String
        fechaFin: String
        eliminado: String
    }

    type Query {
        informeDonaciones(
            categoria: String,
            fechaInicio: String,
            fechaFin: String,
            eliminado: String
        ): [ResultadoDonaciones]

        listarFiltrosDonaciones: [FiltroDonacion]
    }

    type Mutation {
        guardarFiltroDonaciones(filtro: FiltroDonacionInput!): FiltroDonacion
        editarFiltroDonaciones(id: ID!, filtro: FiltroDonacionInput!): FiltroDonacion
        eliminarFiltroDonaciones(id: ID!): Boolean
    }
"""

# --- 2. Definimos la lógica (resolvers) ---

query = QueryType()
mutation = MutationType()

# Resolver del punto 1: Informe de donaciones
@query.field("informeDonaciones")
def resolve_informe_donaciones(_, info, categoria=None, fechaInicio=None, fechaFin=None, eliminado=None):
    db = SessionLocal()
    q = db.query(Donacion)

    # Aplicar filtros opcionales
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

    # Agrupar resultados
    resultado = {}
    for d in donaciones:
        key = (d.categoria, d.eliminado)
        resultado[key] = resultado.get(key, 0) + d.cantidad

    db.close()

    return [
        {"categoria": k[0], "eliminado": k[1], "totalCantidad": v}
        for k, v in resultado.items()
    ]


# Resolver para listar filtros guardados
@query.field("listarFiltrosDonaciones")
def resolve_listar_filtros_donaciones(_, info):
    db = SessionLocal()
    filtros = db.query(FiltroDonacion).all()
    db.close()

    return [
        {
            "id": f.id,
            "nombre": f.nombre,
            "categoria": f.categoria,
            "fechaInicio": f.fecha_inicio,
            "fechaFin": f.fecha_fin,
            "eliminado": f.eliminado,
        }
        for f in filtros
    ]


# Mutación para guardar filtro
@mutation.field("guardarFiltroDonaciones")
def resolve_guardar_filtro_donaciones(_, info, filtro):
    db = SessionLocal()
    nuevo = FiltroDonacion(
        nombre=filtro["nombre"],
        categoria=filtro.get("categoria"),
        fecha_inicio=filtro.get("fechaInicio"),
        fecha_fin=filtro.get("fechaFin"),
        eliminado=filtro.get("eliminado"),
    )
    db.add(nuevo)
    db.commit()
    db.refresh(nuevo)
    db.close()

    return {
        "id": nuevo.id,
        "nombre": nuevo.nombre,
        "categoria": nuevo.categoria,
        "fechaInicio": nuevo.fecha_inicio,
        "fechaFin": nuevo.fecha_fin,
        "eliminado": nuevo.eliminado,
    }


# Mutación para editar filtro
@mutation.field("editarFiltroDonaciones")
def resolve_editar_filtro_donaciones(_, info, id, filtro):
    db = SessionLocal()
    f = db.query(FiltroDonacion).get(id)
    if not f:
        db.close()
        return None

    # Actualizar solo los campos enviados
    f.nombre = filtro.get("nombre", f.nombre)
    f.categoria = filtro.get("categoria", f.categoria)
    f.fecha_inicio = filtro.get("fechaInicio", f.fecha_inicio)
    f.fecha_fin = filtro.get("fechaFin", f.fecha_fin)
    f.eliminado = filtro.get("eliminado", f.eliminado)

    db.commit()
    db.refresh(f)
    db.close()

    return {
        "id": f.id,
        "nombre": f.nombre,
        "categoria": f.categoria,
        "fechaInicio": f.fecha_inicio,
        "fechaFin": f.fecha_fin,
        "eliminado": f.eliminado,
    }


# Mutación para eliminar filtro
@mutation.field("eliminarFiltroDonaciones")
def resolve_eliminar_filtro_donaciones(_, info, id):
    db = SessionLocal()
    filtro = db.query(FiltroDonacion).get(id)
    if not filtro:
        db.close()
        return False
    db.delete(filtro)
    db.commit()
    db.close()
    return True


# --- 3. Creamos el esquema ejecutable ---
schema = make_executable_schema(type_defs, [query, mutation])

# --- 4. Servidor Flask ---
app = Flask(__name__)
explorer_html = ExplorerGraphiQL().html(None)  # Interfaz visual moderna

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
