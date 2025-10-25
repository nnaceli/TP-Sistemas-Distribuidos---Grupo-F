from db import SessionLocal
from models.Donacion import Donacion
from datetime import datetime

def resolve_informe_donaciones(_, info, categoria=None, fechaInicio=None, fechaFin=None, eliminado=None):
    db = SessionLocal()
    q = db.query(Donacion)

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
