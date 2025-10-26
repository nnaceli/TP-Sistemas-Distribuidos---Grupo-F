from ariadne import QueryType, MutationType, make_executable_schema
from services.donaciones_service import resolve_informe_donaciones
from services.filtros_service import listar_filtros, guardar_filtro, editar_filtro, eliminar_filtro

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

query = QueryType()
mutation = MutationType()

# --- Queries ---
query.set_field("informeDonaciones", resolve_informe_donaciones)
query.set_field("listarFiltrosDonaciones", lambda *_: listar_filtros())

# --- Mutations ---
mutation.set_field("guardarFiltroDonaciones", lambda *_, filtro: guardar_filtro(filtro))
mutation.set_field("editarFiltroDonaciones", lambda *_, id, filtro: editar_filtro(id, filtro))
mutation.set_field("eliminarFiltroDonaciones", lambda *_, id: eliminar_filtro(id))

schema = make_executable_schema(type_defs, [query, mutation])
