import grpc
import sys
import os

# Agregamos la ruta donde están los archivos generados
PROTO_DIR = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', 'Proto', 'login'))
sys.path.append(PROTO_DIR)

from Proto.Login import login_pb2_grpc, login_pb2

# Crear canal y stub globales
channel = grpc.insecure_channel('localhost:9091')
stub = login_pb2_grpc.LoginServiceStub(channel)

def login(username, password):
    login_request = login_pb2.LoginRequest(username=username, password=password)
    return stub.Login(login_request)   