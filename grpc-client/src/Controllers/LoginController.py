import sys
import os

SRC_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
sys.path.append(SRC_DIR)

from flask import Blueprint, request, jsonify
from GrpcService.GrpcLoginService import login
from grpc import RpcError

login_bp = Blueprint('login_bp', __name__)

@login_bp.route('', methods=['POST'])
def login_route():
    try:
        data = request.json
        respuesta = login(
            username=data['username'],
            password=data['password']
        )
        return jsonify({
            "token": respuesta.token,
            "username": respuesta.username.username,
            "rol": respuesta.rol.nombre,
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error en el login"
        return jsonify({"error": mensaje}), 404