import sys
import os
from flask import Blueprint, request, jsonify
from grpc import RpcError

#SRC_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
#sys.path.append(SRC_DIR)

from GrpcService.GrpcDonacionService import (
    crear_donacion,
    actualizar_donacion,
    eliminar_donacion,
    obtener_donaciones_por_usuario
)

donacion_bp = Blueprint('donacion_bp', __name__)

@donacion_bp.route('/crear', methods=['POST'])
def crear():
    try:
        data = request.json
        response = crear_donacion(
            categoria=data['categoria'],
            descripcion=data['descripcion'],
            cantidad=data['cantidad'],
            usuario=data['usuario']
        )
        return jsonify({
            "status": response.status,
            "message": response.message
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al crear la donación, verifique los datos ingresados"
        return jsonify({"error": mensaje}), 404

@donacion_bp.route('/<int:donacion_id>', methods=['PUT'])
def actualizar(donacion_id):
    try:
        data = request.json
        response = actualizar_donacion(
            id=donacion_id,
            categoria=data['categoria'],
            descripcion=data['descripcion'],
            cantidad=data['cantidad'],
            usuario=data['usuario']
        )
        return jsonify({
            "status": response.status,
            "message": response.message
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al actualizar la donación"
        return jsonify({"error": mensaje}), 404

@donacion_bp.route('/<int:donacion_id>', methods=['DELETE'])
def eliminar(donacion_id):
    try:
        response = eliminar_donacion(donacion_id)
        return jsonify({
            "status": response.status,
            "message": response.message
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al eliminar la donación"
        return jsonify({"error": mensaje}), 404

@donacion_bp.route('/listar/<string:username>', methods=['GET'])
def listar_por_usuario(username):
    try:
        donaciones = obtener_donaciones_por_usuario(username)
        resultado = []
        for d in donaciones:
            resultado.append({
                "id": d.id,
                "categoria": d.categoria,
                "descripcion": d.descripcion,
                "cantidad": d.cantidad,
                "usuario": d.usuario
            })
        return jsonify(resultado)
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al listar las donaciones"
        return jsonify({"error": mensaje}), 404