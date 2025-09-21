import grpc
from donacion_pb2 import Donacion, DonacionRequest, DonacionUserRequest, DonacionUserResponse
from donacion_pb2_grpc import DonacionServiceStub

# Se establece la conexión con el servidor gRPC
def get_donacion_stub():
    channel = grpc.insecure_channel('localhost:50051')
    return DonacionServiceStub(channel)

def crear_donacion(categoria, descripcion, cantidad, usuario):
    stub = get_donacion_stub()
    donacion_nueva = Donacion(
        categoria=categoria,
        descripcion=descripcion,
        cantidad=cantidad,
        usuario=usuario
    )
    response = stub.CreateDonacion(donacion_nueva)
    return response

def actualizar_donacion(id, categoria, descripcion, cantidad, usuario):
    stub = get_donacion_stub()
    donacion_actualizada = Donacion(
        id=id,
        categoria=categoria,
        descripcion=descripcion,
        cantidad=cantidad,
        usuario=usuario
    )
    response = stub.UpdateDonacion(donacion_actualizada)
    return response

def eliminar_donacion(id):
    stub = get_donacion_stub()
    response = stub.DeleteDonacion(DonacionRequest(id=id))
    return response

def obtener_donaciones_por_usuario(username):
    stub = get_donacion_stub()
    response = stub.GetDonacionUser(DonacionUserRequest(username=username))
    return response.donaciones