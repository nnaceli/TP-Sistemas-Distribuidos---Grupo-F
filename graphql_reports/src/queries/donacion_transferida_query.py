from models import DonacionTransferida


# query = QueryType()

# @query.field("donacion_transferida")
def resolve_donaciones_transferidas(obj, info):
    try:
        donaciones = [DonacionTransferida.repr() for donacion in DonacionTransferida.query.all()]
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
