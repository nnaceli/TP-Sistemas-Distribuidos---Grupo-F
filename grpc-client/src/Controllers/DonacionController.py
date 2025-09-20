from flask import Blueprint, request, jsonify
from gpc_client.services.grpcDonacionesService import DonacionesClient
from gpc_client.proto.donacion import donacion_pb2 # Para acceder a Enums y Empty
import grpc
import logging

# Configura el logger
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Crea un Blueprint para el controlador de donaciones
donaciones_bp = Blueprint('donaciones', __name__)

# Inicializa el cliente gRPC de Donaciones
donaciones_client = DonacionesClient()

@donaciones_bp.route('/donaciones', methods=['POST'])
def create_donacion_controller():
    data = request.json
    try:
        categoria = data['categoria']
        descripcion = data['descripcion']
        cantidad = data['cantidad']

        # Llama al método del cliente gRPC
        response_pb = donaciones_client.create_donacion(categoria, descripcion, cantidad)

        # Convierte la respuesta protobuf a un diccionario JSON
        return jsonify({
            "categoria": donacion_pb2.DonacionCategoria.Name(response_pb.categoria),
            "descripcion": response_pb.descripcion,
            "cantidad": response_pb.cantidad
        }), 201 # 201 Created
    except KeyError as e:
        logger.error(f"Faltan datos para crear donación: {e}")
        return jsonify({"error": f"Missing data for {e}"}), 400
    except grpc.RpcError as e:
        logger.error(f"gRPC error creating donacion: {e.details}")
        return jsonify({"error": e.details}), 500
    except Exception as e:
        logger.error(f"Unexpected error: {e}")
        return jsonify({"error": "Internal Server Error"}), 500

@donaciones_bp.route('/donaciones/<int:donacion_id>', methods=['GET'])
def get_donacion_controller(donacion_id):
    try:
        # Llama al método del cliente gRPC
        response_pb = donaciones_client.get_donacion(donacion_id)

        # Convierte la respuesta protobuf a un diccionario JSON
        return jsonify({
            "id": response_pb.id,
            "categoria": donacion_pb2.DonacionCategoria.Name(response_pb.categoria),
            "descripcion": response_pb.descripcion,
            "cantidad": response_pb.cantidad,
            "eliminado": response_pb.eliminado,
            # Las timestamps necesitan un formato específico si se quieren mostrar bonitas
            "fechaCreacion": response_pb.fechaCreacion.ToJsonString() if response_pb.HasField('fechaCreacion') else None,
            "usernameCreacion": response_pb.usernameCreacion,
            "fechaModificacion": response_pb.fechaModificacion.ToJsonString() if response_pb.HasField('fechaModificacion') else None,
            "usernameModificacion": response_pb.usernameModificacion,
        })
    except grpc.RpcError as e:
        logger.error(f"gRPC error getting donacion {donacion_id}: {e.details}")
        status_code = 404 if e.code() == grpc.StatusCode.NOT_FOUND else 500
        return jsonify({"error": e.details}), status_code
    except Exception as e:
        logger.error(f"Unexpected error: {e}")
        return jsonify({"error": "Internal Server Error"}), 500

@donaciones_bp.route('/donaciones', methods=['PUT']) # O /donaciones/<int:donacion_id> si el ID fuera parte de la URL
def update_donacion_controller():
    data = request.json
    try:
        # Aquí se asume que el DTO del .proto ya tiene los campos modificables
        # Si el ID es necesario para el Update, el .proto debería ser revisado.
        # Por ahora, se envía el DTO como está en el request.
        categoria = data['categoria']
        descripcion = data['descripcion']
        cantidad = data['cantidad']

        response_pb = donaciones_client.update_donacion(categoria, descripcion, cantidad)

        return jsonify({
            "categoria": donacion_pb2.DonacionCategoria.Name(response_pb.categoria),
            "descripcion": response_pb.descripcion,
            "cantidad": response_pb.cantidad
        })
    except KeyError as e:
        logger.error(f"Faltan datos para actualizar donación: {e}")
        return jsonify({"error": f"Missing data for {e}"}), 400
    except grpc.RpcError as e:
        logger.error(f"gRPC error updating donacion: {e.details}")
        return jsonify({"error": e.details}), 500
    except Exception as e:
        logger.error(f"Unexpected error: {e}")
        return jsonify({"error": "Internal Server Error"}), 500

@donaciones_bp.route('/donaciones/<int:donacion_id>', methods=['DELETE'])
def delete_donacion_controller(donacion_id):
    try:
        # Llama al método del cliente gRPC
        donaciones_client.delete_donacion(donacion_id)
        return jsonify({"message": f"Donación con ID {donacion_id} marcada como eliminada."})
    except grpc.RpcError as e:
        logger.error(f"gRPC error deleting donacion {donacion_id}: {e.details}")
        return jsonify({"error": e.details}), 500
    except Exception as e:
        logger.error(f"Unexpected error: {e}")
        return jsonify({"error": "Internal Server Error"}), 500

@donaciones_bp.route('/donaciones', methods=['GET'])
def list_donaciones_controller():
    try:
        # Llama al método del cliente gRPC
        response_pb = donaciones_client.list_donaciones()

        # Convierte la lista de protobuf a una lista de diccionarios JSON
        donaciones_json = []
        for donacion in response_pb.donaciones:
            donaciones_json.append({
                "id": donacion.id,
                "categoria": donacion_pb2.DonacionCategoria.Name(donacion.categoria),
                "descripcion": donacion.descripcion,
                "cantidad": donacion.cantidad,
                "eliminado": donacion.eliminado,
                "fechaCreacion": donacion.fechaCreacion.ToJsonString() if donacion.HasField('fechaCreacion') else None,
                "usernameCreacion": donacion.usernameCreacion,
                "fechaModificacion": donacion.fechaModificacion.ToJsonString() if donacion.HasField('fechaModificacion') else None,
                "usernameModificacion": donacion.usernameModificacion,
            })
        return jsonify(donaciones_json)
    except grpc.RpcError as e:
        logger.error(f"gRPC error listing donaciones: {e.details}")
        return jsonify({"error": e.details}), 500
    except Exception as e:
        logger.error(f"Unexpected error: {e}")
        return jsonify({"error": "Internal Server Error"}), 500