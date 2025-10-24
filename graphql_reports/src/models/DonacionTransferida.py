from datetime import datetime
from sqlalchemy import Column, BigInteger, Integer, String, Boolean, DateTime
import database

Base = database.db.Model

class DonacionRecibida(Base):
    __tablename__ = "donacion_requerida"

    id = Column(BigInteger, primary_key=True, autoincrement=True)
    # cantidad = Column(Integer, nullable=False)
    categoria = Column(String(255), nullable=False)
    descripcion = Column(String(255), nullable=False)
    solicitud_id = Column(BigInteger, nullable=False)

    def __repr__(self):
        return (
            f"<DonacionTrasferida(id={self.id}, cantidad={self.cantidad},"
            f"descripcion='{self.descripcion}', "
            f"solicitud_id={self.solicitud_id})>"
        )