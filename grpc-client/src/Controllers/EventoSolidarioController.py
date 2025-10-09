import os
import sys
from flask import Blueprint, request, jsonify
from grpc import RpcError
from datetime import datetime, timezone

from GrpcService.GrpcEventoSolidarioService import (
    crear_evento,
    obtener_evento,
    actualizar_evento,
    eliminar_evento,
    listar_eventos
)


from GrpcService.GrpcUsuarioService import (
    obtener_usuario
)

def extraer_token():
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return None
    return auth_header.split(" ")[1]


evento_bp = Blueprint('evento_bp', __name__)

@evento_bp.route('/crear', methods=['POST'])
def crear():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
        fecha_str = data['fecha']
        # Convertir la fecha de string a un objeto datetime de Python
        fecha_evento = datetime.fromisoformat(fecha_str).astimezone(timezone.utc)


        # Buscar y agregar miembros
        miembros_pb = []
        for username in data['miembros']:
            usuario = obtener_usuario(username, token)
            if usuario is None:
                raise Exception(f"Miembro a agregar '{username}' no encontrado")
            miembros_pb.append(usuario)

        response = crear_evento(
            nombre=data['nombre'],
            descripcion=data['descripcion'],
            fecha=fecha_evento,
            miembros=miembros_pb,
            token=token
        )
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "descripcion": response.descripcion,
            "fecha": response.fecha.ToDatetime().isoformat(),
            "miembros": [m.username for m in response.miembros]
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al crear el evento"
        return jsonify({"error": mensaje}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 400

@evento_bp.route('/<int:evento_id>', methods=['GET'])
def obtener(evento_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        evento = obtener_evento(evento_id, token)
        return jsonify({
            "id": evento.id,
            "nombre": evento.nombre,
            "descripcion": evento.descripcion,
            "fecha": evento.fecha.ToDatetime().isoformat(),
            "miembros": [m.username for m in evento.miembros]
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Evento no encontrado"
        return jsonify({"error": mensaje}), 404

@evento_bp.route('/<int:evento_id>', methods=['PUT'])
def actualizar(evento_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
        fecha_str = data['fecha']
        fecha_evento = datetime.fromisoformat(fecha_str)


        # Buscar y agregar miembros
        miembros_pb = [obtener_usuario(username, token) for username in data['miembros']]

        response = actualizar_evento(
            nombre=data['nombre'],
            descripcion=data['descripcion'],
            fecha=fecha_evento,
            miembros=miembros_pb,
            token=token
        )
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "descripcion": response.descripcion,
            "fecha": response.fecha.ToDatetime().isoformat(),
            "miembros": [m.username for m in response.miembros]
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al actualizar evento"
        return jsonify({"error": mensaje}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 400

@evento_bp.route('/inscribirMiembro/<int:evento_id>', methods=['PUT'])
def inscribir_miembro(evento_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json

        evento = obtener_evento( evento_id, token)
        # agregar miembros a los ya existentes
        miembros_existentes = evento.miembros
        miembros_pb = miembros_existentes
        for username in data['miembros']:
            usuario = obtener_usuario(username, token)
            if usuario is None:
                raise Exception(f"Miembro a agregar '{username}' no encontrado")
            else:
                if usuario in miembros_existentes:
                    raise Exception(f"Miembro a agregar '{username}' ya pertenece al evento")

            miembros_pb.append(usuario)

        response = actualizar_evento(
            nombre=evento.nombre,
            descripcion=evento.descripcion,
            fecha=evento.fecha.ToDatetime(),
            miembros=miembros_pb,
            token=token
        )
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "descripcion": response.descripcion,
            "fecha": response.fecha.ToDatetime().isoformat(),
            "miembros": [m.username for m in response.miembros]
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al inscribir miembros"
        return jsonify({"error": mensaje}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 400

@evento_bp.route('/<int:evento_id>', methods=['DELETE'])
def eliminar(evento_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        response = eliminar_evento(evento_id, token)
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "mensaje": f"Evento {response.nombre} eliminado correctamente"
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al eliminar evento"
        return jsonify({"error": mensaje}), 404


@evento_bp.route('/listar', methods=['GET'])
def listar():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        eventos = listar_eventos(token)
        resultado = []
        for evento in eventos:
            resultado.append({
                "id": evento.id,
                "nombre": evento.nombre,
                "descripcion": evento.descripcion,
                "fecha": evento.fecha.ToDatetime().isoformat(),
                "miembros": [m.username for m in evento.miembros]
            })
        return jsonify(resultado)
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al listar eventos"
        return jsonify({"error": mensaje}), 404
