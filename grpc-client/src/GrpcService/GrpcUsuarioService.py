import grpc
import sys
import os
from google.protobuf.json_format import MessageToJson

# Agregamos la ruta donde están los archivos generados
PROTO_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', 'Proto', 'usuario'))
sys.path.append(PROTO_DIR)

import usuario_pb2
import usuario_pb2_grpc

# Crear canal y stub globales
channel = grpc.insecure_channel('localhost:9091')
stub = usuario_pb2_grpc.UsuarioServiceStub(channel)

def crear_usuario(username, nombre, apellido, telefono, email, rol_nombre):
    rol = usuario_pb2.Rol(nombre=rol_nombre)
    usuario_dto = usuario_pb2.UsuarioDTO(
        username=username,
        nombre=nombre,
        apellido=apellido,
        telefono=telefono,
        email=email,
        rol=rol
    )
    return stub.CreateUsuario(usuario_dto)

def obtener_usuario(username):
    consulta = usuario_pb2.Username(username=username)
    return stub.GetUsuario(consulta)

def listar_usuarios():
    respuesta = stub.ListUsuarios(usuario_pb2.Empty())
    return respuesta.usuarios

def eliminar_usuario(username):
    consulta = usuario_pb2.Username(username=username)
    return stub.DeleteUsuario(consulta)

def actualizar_usuario(username, nombre, apellido, telefono, email, rol_nombre):
    rol = usuario_pb2.Rol(nombre=rol_nombre)
    usuario_dto = usuario_pb2.UsuarioDTO(
        username=username,
        nombre=nombre,
        apellido=apellido,
        telefono=telefono,
        email=email,
        rol=rol
    )
    return stub.UpdateUsuario(usuario_dto)