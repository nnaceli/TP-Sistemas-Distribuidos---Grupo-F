import grpc
from Proto.Donacion import donacion_pb2 as pb2
from Proto.Donacion import donacion_pb2_grpc as pb2_grpc

# Se establece la conexión con el servidor gRPC
def get_donacion_stub():
    channel = grpc.insecure_channel('localhost:50051')
    return pb2_grpc.DonacionServiceStub(channel)

def crear_donacion(categoria, descripcion, cantidad, usuario):
    stub = get_donacion_stub()
    donacion_nueva = pb2.Donacion(
        categoria=categoria,
        descripcion=descripcion,
        cantidad=cantidad,
        usuario=usuario
    )
    response = stub.CreateDonacion(donacion_nueva)
    return response

def actualizar_donacion(id, categoria, descripcion, cantidad, usuario):
    stub = get_donacion_stub()
    donacion_actualizada = pb2.Donacion(
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
    response = stub.DeleteDonacion(pb2.DonacionRequest(id=id))
    return response

def obtener_donaciones_por_usuario(username):
    stub = get_donacion_stub()
    response = stub.GetDonacionUser(pb2.DonacionUserRequest(username=username))
    return response.donaciones