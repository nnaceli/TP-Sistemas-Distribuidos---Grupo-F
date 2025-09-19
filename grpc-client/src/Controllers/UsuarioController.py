import sys
import os

SRC_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(SRC_DIR)

from flask import Blueprint, request, jsonify
from GrpcService.GrpcUsuarioService import crear_usuario, obtener_usuario, listar_usuarios, eliminar_usuario, actualizar_usuario
from grpc import RpcError

usuario_bp = Blueprint('usuario_bp', __name__)

@usuario_bp.route('/crear', methods=['POST'])
def crear():
    try:
        data = request.json
        usuario = crear_usuario(
            username=data['username'],
            nombre=data['nombre'],
            apellido=data['apellido'],
            telefono=data['telefono'],
            email=data['email'],
            rol_nombre=data['rol']
        )
        return jsonify({
            "username": usuario.username,
            "nombre": usuario.nombre,
            "apellido": usuario.apellido,
            "telefono": usuario.telefono,
            "email": usuario.email,
            "rol": usuario.rol.nombre
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al crear usuario"
        return jsonify({"error": mensaje}), 404

@usuario_bp.route('/<username>', methods=['GET'])
def obtener(username):
    try:
        usuario = obtener_usuario(username)
        return jsonify({
            "id": usuario.id,
            "username": usuario.username,
            "nombre": usuario.nombre,
            "apellido": usuario.apellido,
            "telefono": usuario.telefono,
            "email": usuario.email,
            "rol": usuario.rol.nombre,
            "activo": usuario.activo
        })
   
    except RpcError as e:
        mensaje = e.details() if e.details() else "Usuario no encontrado"
        return jsonify({"error": mensaje}), 404

@usuario_bp.route('/listar', methods=['GET'])
def listar():
    try:
        usuarios = listar_usuarios()
        resultado = []
        for u in usuarios:
            resultado.append({
                "id": u.id,
                "username": u.username,
                "nombre": u.nombre,
                "apellido": u.apellido,
                "telefono": u.telefono,
                "email": u.email,
                "rol": u.rol.nombre,
                "activo": u.activo
            })
        return jsonify(resultado)

    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al listar usuarios"
        return jsonify({"error": mensaje}), 404

@usuario_bp.route('/<username>', methods=['DELETE'])
def eliminar(username):
    try:
        eliminar_usuario(username)
        return jsonify({"mensaje": f"Usuario {username} eliminado correctamente"})

    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al eliminar usuario"
        return jsonify({"error": mensaje}), 404

@usuario_bp.route('/<username>', methods=['PUT'])
def actualizar(username):
    try:
        data = request.json
        usuario = actualizar_usuario(
            username=username,
            nombre=data['nombre'],
            apellido=data['apellido'],
            telefono=data['telefono'],
            email=data['email'],
            rol_nombre=data['rol']
        )
        return jsonify({
            "username": usuario.username,
            "nombre": usuario.nombre,
            "apellido": usuario.apellido,
            "telefono": usuario.telefono,
            "email": usuario.email,
            "rol": usuario.rol.nombre
        })

    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al actualizar usuario"
        return jsonify({"error": mensaje}), 404