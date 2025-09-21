import grpc
from google.protobuf.timestamp_pb2 import Timestamp
from eventoSolidario_pb2 import (
    EventoSolidario, EventoSolidarioDTO, EventoSolidarioListResponse, EventoIdRequest
)
from eventoSolidario_pb2_grpc import EventoSolidarioServiceStub
from usuario_pb2 import Usuario, Empty
from grpc import RpcError

# Función de utilidad para obtener el stub del servicio gRPC
def get_evento_stub():
    channel = grpc.insecure_channel('localhost:50051')
    return EventoSolidarioServiceStub(channel)

def crear_evento(nombre, descripcion, fecha, miembros):
    try:
        stub = get_evento_stub()
        # Convertir la fecha de Python a un objeto Timestamp de Protobuf
        fecha_timestamp = Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = EventoSolidarioDTO(
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
        response = stub.GetEventoSolidario(EventoSolidario(id=id))
        return response
    except RpcError as e:
        raise e

def actualizar_evento(nombre, descripcion, fecha, miembros):
    try:
        stub = get_evento_stub()
        fecha_timestamp = Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = EventoSolidarioDTO(
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
        response = stub.DeleteEventoSolidario(EventoSolidario(id=id))
        return response
    except RpcError as e:
        raise e

def listar_eventos():
    try:
        stub = get_evento_stub()
        response = stub.ListEventoSolidarios(Empty())
        return response.eventos
    except RpcError as e:
        raise e