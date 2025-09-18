import sys
import os

SRC_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(SRC_DIR)

from flask import Flask, request, jsonify
from GrpcService.GrpcUsuarioService import crear_usuario, obtener_usuario, listar_usuarios, eliminar_usuario, actualizar_usuario

app = Flask(__name__)

@app.route('/usuarios', methods=['POST'])
def crear():
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

@app.route('/usuarios/<username>', methods=['GET'])
def obtener(username):
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

@app.route('/usuarios', methods=['GET'])
def listar():
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

@app.route('/usuarios/<username>', methods=['DELETE'])
def eliminar(username):
    eliminar_usuario(username)
    return jsonify({"mensaje": f"Usuario {username} eliminado correctamente"})

@app.route('/usuarios/<username>', methods=['PUT'])
def actualizar(username):
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

if __name__ == '__main__':
    app.run(debug=True, port=5000)
