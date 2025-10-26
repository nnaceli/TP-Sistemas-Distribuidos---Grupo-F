from db import SessionLocal
from models.Usuario import Usuario
from models.Evento import Evento
from models.EventoUsuarios import evento_usuarios
from sqlalchemy import and_

def obtener_eventos_por_usuario(username, fecha_inicio, fecha_fin):
    db = SessionLocal()

    usuario = db.query(Usuario).filter(Usuario.username == username).first()
    if not usuario:
        db.close()
        return []

    query = (
        db.query(Evento)
        .join(evento_usuarios, Evento.id == evento_usuarios.c.evento_id)
        .filter(evento_usuarios.c.usuario_id == usuario.id)
    )

    if fecha_inicio:
        query = query.filter(Evento.fecha_hora >= fecha_inicio)
    if fecha_fin:
        query = query.filter(Evento.fecha_hora <= fecha_fin)

    eventos = query.all()

    resultado = [
        {
            "id": e.id,
            "descripcion": e.descripcion,
            "fecha_hora": e.fecha_hora.isoformat(),
            "nombre_evento": e.nombre_evento,
        }
        for e in eventos
    ]

    db.close()
    return resultado