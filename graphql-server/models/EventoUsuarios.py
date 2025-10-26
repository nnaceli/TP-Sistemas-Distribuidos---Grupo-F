from sqlalchemy import Table, Column, Integer, ForeignKey
from db import Base

evento_usuarios = Table(
    "evento_usuarios",
    Base.metadata,
    Column("evento_id", Integer, ForeignKey("evento.id")),
    Column("usuario_id", Integer, ForeignKey("usuario.id")),
)