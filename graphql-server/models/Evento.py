from sqlalchemy import Column, Integer, String, DateTime
from db import Base

class Evento(Base):
    __tablename__ = "evento"
    id = Column(Integer, primary_key=True, index=True)
    descripcion = Column(String)
    fecha_hora = Column(DateTime)
    nombre_evento = Column(String)