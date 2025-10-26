from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# Configuración de conexión a PostgreSQL
DB_NAME = "tp_grpc"
DB_USER = "postgres"
DB_PASS = "44302429"
DB_HOST = "localhost"
DB_PORT = "5432"

DATABASE_URL = f"postgresql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()


#if __name__ == "__main__":
#    from models import Donacion
#    Base.metadata.create_all(bind=engine)
#    print("Tablas creadas correctamente")

