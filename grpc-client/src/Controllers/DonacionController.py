from flask import Blueprint, request, jsonify
from grpc import RpcError

from GrpcService.GrpcDonacionService import (
    crear_donacion,
    actualizar_donacion,
    eliminar_donacion,
    obtener_donaciones_por_id,
    listar_las_donaciones
)

donacion_bp = Blueprint('donacion_bp', __name__)

def extraer_token():
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return None
    return auth_header.split(" ")[1]

@donacion_bp.route('/crear', methods=['POST'])
def crear():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
    
        if 'categoria' not in data or 'descripcion' not in data or 'cantidad' not in data:
            return jsonify({"error": "Faltan datos requeridos (categoria, descripcion, cantidad)."}), 400
        
        donacion = crear_donacion(
            categoria=data['categoria'],
            descripcion=data['descripcion'],
            cantidad=data['cantidad'],
            token=token
        )
        return jsonify({
            "categoria": donacion.categoria,
            "descripcion": donacion.descripcion,
            "cantidad": donacion.cantidad
        })
    
    except RpcError as e:
        # Manejo de errores de gRPC (ej. errores de validación)
        mensaje = e.details() if e.details() else "Error al crear la donación. Verifique los datos."
        print(f"ERROR GRPc en crear(): {mensaje}") # Imprime en consola para debug
        return jsonify({"error": mensaje}), 400 # 400 Bad Request es más apropiado aquí
        
    except Exception as e:
        import traceback
        print("--------------------------------------------------")
        print("ERROR FATAL EN CREAR DONACION (PYTHON TRACEBACK):")
        traceback.print_exc() # Imprime el error completo en la consola de Python
        print("--------------------------------------------------")
        return jsonify({"error": f"Error interno del servidor. Consulte la consola de Python."}), 500

def actualizar():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        data = request.json
        donacion_actualizada = actualizar_donacion(
            categoria=data['categoria'],
            descripcion=data['descripcion'],
            cantidad=data['cantidad'],
            token=token
        )
        return jsonify({
            "categoria": donacion_actualizada.categoria,
            "descripcion": donacion_actualizada.descripcion,
            "cantidad": donacion_actualizada.cantidad
        })
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al actualizar la donación"
        return jsonify({"error": mensaje}), 404

@donacion_bp.route('/<int:donacion_id>', methods=['DELETE'])
def eliminar(donacion_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        eliminar_donacion(donacion_id, token)
        return jsonify({"mensaje": f"Donacion {donacion_id} eliminada correctamente"})
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al eliminar la donación"
        return jsonify({"error": mensaje}), 404

@donacion_bp.route('/listar', methods=['GET'])
def listar_donaciones():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        donaciones = listar_las_donaciones(token)
        resultado = []
        for d in donaciones:
            resultado.append({
                "id": d.id,
                "categoria": d.categoria,
                "descripcion": d.descripcion,
                "cantidad": d.cantidad
            })
        return jsonify(resultado)
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al listar las donaciones"
        return jsonify({"error": mensaje}), 404


@donacion_bp.route('/<int:donacion_id>', methods=['GET'])
def obtener(donacion_id):
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401

        donacion = obtener_donaciones_por_id( donacion_id,token)
        return jsonify(
            {
                "id": donacion.id,
                "categoria": donacion.categoria,
                "descripcion": donacion.descripcion,
                "cantidad": donacion.cantidad
            }
        )
    except RpcError as e:
        mensaje = e.details() if e.details() else "Error al listar las donaciones"
        return jsonify({"error": mensaje}), 404