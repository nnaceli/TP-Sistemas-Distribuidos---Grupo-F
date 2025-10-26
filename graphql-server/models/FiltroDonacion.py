from sqlalchemy import Column, Integer, String
from db import Base

class FiltroDonacion(Base):
    __tablename__ = "filtro_donacion"

    id = Column(Integer, primary_key=True, index=True)
    nombre = Column(String, nullable=False)
    categoria = Column(String)
    fecha_inicio = Column(String)
    fecha_fin = Column(String)
    eliminado = Column(String)
