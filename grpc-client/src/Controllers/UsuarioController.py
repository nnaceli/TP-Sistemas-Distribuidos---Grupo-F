from flask import Blueprint, request, jsonify
from GrpcService.GrpcUsuarioService import crear_usuario, obtener_usuario, listar_usuarios, eliminar_usuario, actualizar_usuario
from grpc import RpcError

usuario_bp = Blueprint('usuario_bp', __name__)

def extraer_token():
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return None
    return auth_header.split(" ")[1]

@usuario_bp.route('/crear', methods=['POST'])
def crear():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
        usuario = crear_usuario(
            username=data['username'],
            nombre=data['nombre'],
            apellido=data['apellido'],
            telefono=data['telefono'],
            email=data['email'],
            rol_nombre=data['rol'],
            token=token
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
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        usuario = obtener_usuario(username, token)
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
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        usuarios = listar_usuarios(token)
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
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        eliminar_usuario(username, token)
        return jsonify({"mensaje": f"Usuario {username} eliminado correctamente"})
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al eliminar usuario"
        return jsonify({"error": mensaje}), 404

@usuario_bp.route('/<username>', methods=['PUT'])
def actualizar(username):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
        usuario = actualizar_usuario(
            username=username,
            nombre=data['nombre'],
            apellido=data['apellido'],
            telefono=data['telefono'],
            email=data['email'],
            rol_nombre=data['rol'],
            token=token
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
