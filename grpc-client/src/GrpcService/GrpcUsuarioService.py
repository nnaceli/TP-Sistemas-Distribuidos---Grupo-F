import grpc
import sys
import os
from google.protobuf.json_format import MessageToJson

# Agregamos la ruta donde están los archivos generados
PROTO_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', 'Proto', 'Usuario'))
sys.path.append(PROTO_DIR)

import usuario_pb2
import usuario_pb2_grpc

channel = grpc.insecure_channel('localhost:9091')
stub = usuario_pb2_grpc.UsuarioServiceStub(channel)

def crear_usuario(username, nombre, apellido, telefono, email, rol_nombre, token):
    metadata = [('authorization', f'Bearer {token}')]
    rol = usuario_pb2.Rol(nombre=rol_nombre)
    usuario_dto = usuario_pb2.UsuarioDTO(
        username=username,
        nombre=nombre,
        apellido=apellido,
        telefono=telefono,
        email=email,
        rol=rol
    )
    return stub.CreateUsuario(usuario_dto, metadata=metadata)

def obtener_usuario(username, token):
    metadata = [('authorization', f'Bearer {token}')]
    consulta = usuario_pb2.Username(username=username)
    return stub.GetUsuario(consulta, metadata=metadata)

def listar_usuarios(token):
    metadata = [('authorization', f'Bearer {token}')]
    respuesta = stub.ListUsuarios(usuario_pb2.Empty(), metadata=metadata)
    return respuesta.usuarios

def eliminar_usuario(username, token):
    metadata = [('authorization', f'Bearer {token}')]
    consulta = usuario_pb2.Username(username=username)
    return stub.DeleteUsuario(consulta, metadata=metadata)

def actualizar_usuario(username, nombre, apellido, telefono, email, rol_nombre, token):
    metadata = [('authorization', f'Bearer {token}')]
    rol = usuario_pb2.Rol(nombre=rol_nombre)
    usuario_dto = usuario_pb2.UsuarioDTO(
        username=username,
        nombre=nombre,
        apellido=apellido,
        telefono=telefono,
        email=email,
        rol=rol
    )
    return stub.UpdateUsuario(usuario_dto, metadata=metadata)
