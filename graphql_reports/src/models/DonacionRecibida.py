from datetime import datetime
from sqlalchemy import Column, BigInteger, Integer, String, Boolean, DateTime
from sqlalchemy.orm import declarative_base
import database
Base = database.db.Model

class DonacionRecibida(Base):
    __tablename__ = "donacion"

    id = Column(BigInteger, primary_key=True, autoincrement=True)
    cantidad = Column(Integer, nullable=False)
    categoria = Column(String(255), nullable=False)
    descripcion = Column(String(255), nullable=False)
    eliminado = Column(Boolean, nullable=False, default=False)
    fecha_alta = Column(DateTime, nullable=False, default=datetime.now)
    fecha_modificacion = Column(DateTime, nullable=True)
    usuario_alta = Column(String(255), nullable=False)
    usuario_modificacion = Column(String(255), nullable=True)

    def __repr__(self):
        return (
            f"<DonacionRecibida(id={self.id}, cantidad={self.cantidad}, "
            f"categoria='{self.categoria}', descripcion='{self.descripcion}', "
            f"eliminado={self.eliminado}, fecha_alta={self.fecha_alta}, "
            f"fecha_modificacion={self.fecha_modificacion}, "
            f"usuario_alta='{self.usuario_alta}', "
            f"usuario_modificacion='{self.usuario_modificacion}')>"
        )