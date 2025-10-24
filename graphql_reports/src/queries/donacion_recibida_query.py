from models import DonacionRecibida


# query = QueryType()

# @query.field("donacion_recibida")
def resolve_donaciones_recibidas(obj, info):
    try:
        donaciones = [DonacionRecibida.repr() for donacion in DonacionRecibida.query.all()]
        print(donaciones)
        payload = {
            "success": True,
            "donaciones": donaciones
        }
    except Exception as error:
        payload = {
            "success": False,
            "errors": [str(error)]
        }
    return payload
# agregar mas resolvers para:
#CATEGORIA

#RANGO DE FECHAS

#ELIMINADO O NO
