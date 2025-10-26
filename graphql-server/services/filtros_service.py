from db import SessionLocal
from models.FiltroDonacion import FiltroDonacion

def listar_filtros():
    db = SessionLocal()
    filtros = db.query(FiltroDonacion).all()
    db.close()
    return [
        {
            "id": f.id,
            "nombre": f.nombre,
            "categoria": f.categoria,
            "fechaInicio": f.fecha_inicio,
            "fechaFin": f.fecha_fin,
            "eliminado": f.eliminado,
        }
        for f in filtros
    ]

def guardar_filtro(filtro):
    db = SessionLocal()
    nuevo = FiltroDonacion(
        nombre=filtro["nombre"],
        categoria=filtro.get("categoria"),
        fecha_inicio=filtro.get("fechaInicio"),
        fecha_fin=filtro.get("fechaFin"),
        eliminado=filtro.get("eliminado"),
    )
    db.add(nuevo)
    db.commit()
    db.refresh(nuevo)
    db.close()
    return {
        "id": nuevo.id,
        "nombre": nuevo.nombre,
        "categoria": nuevo.categoria,
        "fechaInicio": nuevo.fecha_inicio,
        "fechaFin": nuevo.fecha_fin,
        "eliminado": nuevo.eliminado,
    }

def editar_filtro(id, filtro):
    db = SessionLocal()
    f = db.query(FiltroDonacion).get(id)
    if not f:
        db.close()
        return None
    f.nombre = filtro.get("nombre", f.nombre)
    f.categoria = filtro.get("categoria", f.categoria)
    f.fecha_inicio = filtro.get("fechaInicio", f.fecha_inicio)
    f.fecha_fin = filtro.get("fechaFin", f.fecha_fin)
    f.eliminado = filtro.get("eliminado", f.eliminado)
    db.commit()
    db.refresh(f)
    db.close()
    return {
        "id": f.id,
        "nombre": f.nombre,
        "categoria": f.categoria,
        "fechaInicio": f.fecha_inicio,
        "fechaFin": f.fecha_fin,
        "eliminado": f.eliminado,
    }

def eliminar_filtro(id):
    db = SessionLocal()
    f = db.query(FiltroDonacion).get(id)
    if not f:
        db.close()
        return False
    db.delete(f)
    db.commit()
    db.close()
    return True
