import grpc
from google.protobuf.timestamp_pb2 import Timestamp

from Proto.EventoSolidario import eventoSolidario_pb2 as ev_pb2
from Proto.EventoSolidario import eventoSolidario_pb2_grpc as ev_pb2_grpc
from Proto.usuario import usuario_pb2 as u_pb2

from grpc import RpcError

# Función de utilidad para obtener el stub del servicio gRPC
def get_evento_stub():
    channel = grpc.insecure_channel('localhost:50051')
    return ev_pb2_grpc.EventoSolidarioServiceStub(channel)

def crear_evento(nombre, descripcion, fecha, miembros):
    try:
        stub = get_evento_stub()
        # Convertir la fecha de Python a un objeto Timestamp de Protobuf
        fecha_timestamp = Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = ev_pb2.EventoSolidarioDTO(
            nombre=nombre,
            descripcion=descripcion,
            fecha=fecha_timestamp,
            miembros=miembros
        )
        response = stub.CreateEventoSolidario(evento_dto)
        return response
    except RpcError as e:
        raise e

def obtener_evento(id):
    try:
        stub = get_evento_stub()
        response = stub.GetEventoSolidario(ev_pb2.EventoSolidario(id=id))
        return response
    except RpcError as e:
        raise e

def actualizar_evento(nombre, descripcion, fecha, miembros):
    try:
        stub = get_evento_stub()
        fecha_timestamp = Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = ev_pb2.EventoSolidarioDTO(
            nombre=nombre,
            descripcion=descripcion,
            fecha=fecha_timestamp,
            miembros=miembros
        )
        response = stub.UpdateEventoSolidario(evento_dto)
        return response
    except RpcError as e:
        raise e

def eliminar_evento(id):
    try:
        stub = get_evento_stub()
        response = stub.DeleteEventoSolidario(ev_pb2.EventoSolidario(id=id))
        return response
    except RpcError as e:
        raise e

def listar_eventos():
    try:
        stub = get_evento_stub()
        response = stub.ListEventoSolidarios(u_pb2.Empty())
        return response.eventos
    except RpcError as e:
        raise e