import graphene
from datetime import datetime

# Tipo de datos que devolverá la consulta
class ResultadoDonaciones(graphene.ObjectType):
    categoria = graphene.String()
    eliminado = graphene.Boolean()
    total_cantidad = graphene.Int()

# Clase que define las consultas disponibles
class Query(graphene.ObjectType):
    informe_donaciones = graphene.List(
        ResultadoDonaciones,
        categoria=graphene.String(),
        fecha_inicio=graphene.String(),
        fecha_fin=graphene.String(),
        eliminado=graphene.String()  # "si" | "no" | None
    )

    # Resolver: función que se ejecuta cuando alguien llama a informe_donaciones
    def resolve_informe_donaciones(self, info, categoria=None, fecha_inicio=None, fecha_fin=None, eliminado=None):
        # Datos de ejemplo (después lo vas a conectar a tu base)
        donaciones = [
            {"categoria": "Ropa", "cantidad": 10, "eliminado": False, "fecha_alta": "2025-01-03"},
            {"categoria": "Ropa", "cantidad": 20, "eliminado": False, "fecha_alta": "2025-02-05"},
            {"categoria": "Alimentos", "cantidad": 5, "eliminado": True, "fecha_alta": "2025-03-10"},
            {"categoria": "Alimentos", "cantidad": 15, "eliminado": False, "fecha_alta": "2025-01-20"},
        ]

        def to_date(s):
            if not s:
                return None
            return datetime.fromisoformat(s)

        d_start = to_date(fecha_inicio)
        d_end = to_date(fecha_fin)

        # Filtros
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

        # Agrupar por (categoria, eliminado) y sumar cantidades
        resultado = {}
        for d in filtered:
            key = (d["categoria"], d["eliminado"])
            resultado[key] = resultado.get(key, 0) + d["cantidad"]

        # Retorna los resultados
        return [
            ResultadoDonaciones(categoria=k[0], eliminado=k[1], total_cantidad=v)
            for k, v in resultado.items()
        ]

schema = graphene.Schema(query=Query)
