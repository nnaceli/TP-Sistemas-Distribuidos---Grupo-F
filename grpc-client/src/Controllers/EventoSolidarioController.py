import os
import sys
from flask import Blueprint, request, jsonify
from grpc import RpcError
from datetime import datetime

from GrpcService.GrpcEventoSolidarioService import (
    crear_evento,
    obtener_evento,
    actualizar_evento,
    eliminar_evento,
    listar_eventos
)

from GrpcService.GrpcUsuarioService import listar_usuarios, obtener_usuario;

evento_bp = Blueprint('evento_bp', __name__)

@evento_bp.route('/crear', methods=['POST'])
def crear():
    try:
        data = request.json
        fecha_str = data['fecha']
        # Convertir la fecha de string a un objeto datetime de Python
        fecha_evento = datetime.fromisoformat(fecha_str)

        # Mapear los miembros a un formato de protobuf
        miembros_pb = [listar_usuarios.Usuario(username=username) for username in data['miembros']]

        response = crear_evento(
            nombre=data['nombre'],
            descripcion=data['descripcion'],
            fecha=fecha_evento,
            miembros=miembros_pb
        )
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "descripcion": response.descripcion
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al crear el evento"
        return jsonify({"error": mensaje}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 400

@evento_bp.route('/<int:evento_id>', methods=['GET'])
def obtener(evento_id):
    try:
        evento = obtener_evento(evento_id)
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
        data = request.json
        fecha_str = data['fecha']
        fecha_evento = datetime.fromisoformat(fecha_str)

        miembros_proto = [listar_usuarios.Usuario(username=m['username']) for m in data['miembros']]

        response = actualizar_evento(
            id=evento_id,
            nombre=data['nombre'],
            descripcion=data['descripcion'],
            fecha=fecha_evento,
            miembros=miembros_proto
        )
        return jsonify({
            "id": response.id,
            "nombre": response.nombre,
            "descripcion": response.descripcion
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al actualizar evento"
        return jsonify({"error": mensaje}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 400

@evento_bp.route('/<int:evento_id>', methods=['DELETE'])
def eliminar(evento_id):
    try:
        response = eliminar_evento(evento_id)
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
        eventos = listar_eventos()
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
