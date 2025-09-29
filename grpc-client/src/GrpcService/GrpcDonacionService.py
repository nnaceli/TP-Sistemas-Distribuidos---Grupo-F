from google.protobuf import empty_pb2
import grpc
from Proto.Donacion import donacion_pb2 as pb2
from Proto.Donacion import donacion_pb2_grpc as pb2_grpc

# Se establece la conexión con el servidor gRPC
def get_donacion_stub():
    channel = grpc.insecure_channel('localhost:9091')
    return pb2_grpc.DonacionServiceStub(channel)


def crear_donacion(categoria, descripcion, cantidad, token):
    metadata = [('authorization', f'Bearer {token}')]
    stub = get_donacion_stub()
    try:
        categoria_enum = pb2.DonacionCategoria.Value(categoria.upper())
    except ValueError:
        print(f"Error: Categoría '{categoria}' no es válida.")
        return None
    donacion_nueva = pb2.DonacionDTO(
        categoria=categoria_enum,
        descripcion=descripcion,
        cantidad=cantidad
    )
    response = stub.CreateDonacion(donacion_nueva, metadata=metadata)
    return response


def actualizar_donacion(categoria, descripcion, cantidad, token):
    metadata = [('authorization', f'Bearer {token}')]
    stub = get_donacion_stub()
    donacion_actualizada = pb2.Donacion(
        categoria=categoria,
        descripcion=descripcion,
        cantidad=cantidad
    )
    response = stub.UpdateDonacion(donacion_actualizada, metadata=metadata)

    return response

def eliminar_donacion(id, token):
    metadata = [('authorization', f'Bearer {token}')]
    stub = get_donacion_stub()
    response = stub.DeleteDonacion(pb2.DonacionIdRequest(id=id), metadata= metadata)
    return response

def obtener_donaciones_por_id(id, token):
    metadata = [('authorization', f'Bearer {token}')]
    stub = get_donacion_stub()
    response = stub.GetDonacion(pb2.DonacionIdRequest(id=id), metadata= metadata)
    return response

def listar_las_donaciones(token):
    metadata = [('authorization', f'Bearer {token}')]
    stub = get_donacion_stub()
    response = stub.ListDonaciones(empty_pb2.Empty(), metadata= metadata)
    return response.donaciones