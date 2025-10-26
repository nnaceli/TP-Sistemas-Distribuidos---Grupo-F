from sqlalchemy import Column, Integer, String, Boolean, DateTime
from db import Base
from datetime import datetime

class Donacion(Base):
    __tablename__ = "donacion"  

    id = Column(Integer, primary_key=True, index=True)
    categoria = Column(String, nullable=False)
    descripcion = Column(String)
    cantidad = Column(Integer, nullable=False)
    eliminado = Column(Boolean, default=False)
    fecha_alta = Column(DateTime, default=datetime.now)
    usuario_alta = Column(String)
    usuario_modificacion = Column(String)

class FiltroDonacion(Base):
    __tablename__ = "filtro_donacion"

    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String, nullable=False)
    categoria = Column(String)
    fecha_inicio = Column(String)
    fecha_fin = Column(String)
    eliminado = Column(String)
   
