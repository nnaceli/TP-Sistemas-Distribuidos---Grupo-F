from ariadne import QueryType, make_executable_schema
from services.donaciones_service import resolve_informe_donaciones

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
query.set_field("informeDonaciones", resolve_informe_donaciones)

schema = make_executable_schema(type_defs, query)
