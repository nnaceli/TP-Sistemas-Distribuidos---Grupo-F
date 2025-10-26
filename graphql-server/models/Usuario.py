from sqlalchemy import Column, Integer, String
from db import Base

class Usuario(Base):
    __tablename__ = "usuario"
    id = Column(Integer, primary_key=True, index=True)
    username = Column(String, unique=True, nullable=False)