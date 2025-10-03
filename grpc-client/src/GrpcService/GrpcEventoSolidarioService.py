import grpc
from google.protobuf import timestamp_pb2
from google.protobuf import empty_pb2

from Proto.EventoSolidario import eventoSolidario_pb2 as ev_pb2
from Proto.EventoSolidario import eventoSolidario_pb2_grpc as ev_pb2_grpc

from grpc import RpcError

# Función de utilidad para obtener el stub del servicio gRPC
def get_evento_stub():
    channel = grpc.insecure_channel('localhost:9091')
    return ev_pb2_grpc.EventoSolidarioServiceStub(channel)

def crear_evento(nombre, descripcion, fecha, miembros, token):
    try:
        metadata = [('authorization', f'Bearer {token}')]
        stub = get_evento_stub()
        # Convertir la fecha de Python a un objeto Timestamp de Protobuf
        fecha_timestamp = timestamp_pb2.Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = ev_pb2.EventoSolidarioDTO(
            nombre=nombre,
            descripcion=descripcion,
            fecha=fecha_timestamp,
            miembros=miembros
        )
        response = stub.CreateEventoSolidario(evento_dto,metadata=metadata)
        return response
    except RpcError as e:
        raise e

def obtener_evento(id, token):
    try:
        metadata = [('authorization', f'Bearer {token}')]
        stub = get_evento_stub()
        response = stub.GetEventoSolidario(ev_pb2.EventoIdRequest(id=id),metadata=metadata)
        return response
    except RpcError as e:
        raise e

def actualizar_evento(nombre, descripcion, fecha, miembros, token):
    try:
        metadata = [('authorization', f'Bearer {token}')]
        stub = get_evento_stub()
        fecha_timestamp = timestamp_pb2.Timestamp()
        fecha_timestamp.FromDatetime(fecha)

        evento_dto = ev_pb2.EventoSolidarioDTO(
            nombre=nombre,
            descripcion=descripcion,
            fecha=fecha_timestamp,
            miembros=miembros
        )
        response = stub.UpdateEventoSolidario(evento_dto, metadata=metadata)
        return response
    except RpcError as e:
        raise e

def eliminar_evento(id, token):
    try:
        metadata = [('authorization', f'Bearer {token}')]
        stub = get_evento_stub()
        response = stub.DeleteEventoSolidario(ev_pb2.EventoIdRequest(id=id),metadata=metadata)
        return response
    except RpcError as e:
        raise e

def listar_eventos(token):
    try:
        metadata = [('authorization', f'Bearer {token}')]
        stub = get_evento_stub()
        response = stub.ListEventoSolidarios(empty_pb2.Empty(), metadata=metadata)
        return response.eventos
    except RpcError as e:
        raise e