from flask import Blueprint, request, jsonify
from services.eventos_service import obtener_eventos_por_usuario
from datetime import datetime

evento_bp = Blueprint("evento", __name__)

@evento_bp.route("/eventos-por-usuario", methods=["POST"])
def eventos_por_usuario():
    data = request.get_json()
    username = data.get("username")
    fecha_inicio_raw = data.get("fechaInicio")
    fecha_fin_raw = data.get("fechaFin")
    fecha_inicio = datetime.fromisoformat(fecha_inicio_raw) if fecha_inicio_raw else None
    fecha_fin = datetime.fromisoformat(fecha_fin_raw) if fecha_fin_raw else None

    eventos = obtener_eventos_por_usuario(username, fecha_inicio, fecha_fin)
    return jsonify(eventos), 200