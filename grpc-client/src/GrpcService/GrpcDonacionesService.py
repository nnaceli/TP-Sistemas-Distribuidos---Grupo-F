import grpc
from concurrent import futures

# Importa los protobuf generados a partir de tu .proto
# Asegúrate de que estos archivos estén generados y accesibles
# por ejemplo: from . import donacion_pb2, donacion_pb2_grpc
# Por ahora, los dejaremos como placeholders
# Supongamos que generas estos en una carpeta 'proto' dentro de gpc_client
from gpc_client.proto.donacion import donacion_pb2
from gpc_client.proto.donacion import donacion_pb2_grpc

# Configura el logger para ver mensajes en la consola
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Dirección del servidor gRPC de Java
GRPC_SERVER_ADDRESS = 'localhost:9091' # O el puerto que hayas configurado

class DonacionesClient:
    """
    Cliente gRPC para el servicio de Donaciones.
    Proporciona métodos para interactuar con el servidor de Donaciones.
    """
    def __init__(self, host=GRPC_SERVER_ADDRESS):
        self.channel = grpc.insecure_channel(host)
        self.stub = donacion_pb2_grpc.DonacionServiceStub(self.channel)
        logger.info(f"Cliente de Donaciones gRPC conectado a: {host}")

    def create_donacion(self, categoria, descripcion, cantidad):
        """
        Crea una nueva donación.
        :param categoria: str (ej. "ROPA", "ALIMENTOS")
        :param descripcion: str
        :param cantidad: int
        :return: donacion_pb2.DonacionDTO de la donación creada
        """
        # Convierte la categoría string a su valor enum de protobuf
        try:
            pb_categoria = donacion_pb2.DonacionCategoria.Value(categoria.upper())
        except ValueError:
            logger.error(f"Categoría '{categoria}' inválida. Usando ROPA por defecto.")
            pb_categoria = donacion_pb2.DonacionCategoria.ROPA # O manejar el error de otra forma

        donacion_dto = donacion_pb2.DonacionDTO(
            categoria=pb_categoria,
            descripcion=descripcion,
            cantidad=cantidad
        )
        try:
            response = self.stub.CreateDonacion(donacion_dto)
            logger.info(f"Donación creada exitosamente: {response.descripcion}")
            return response
        except grpc.RpcError as e:
            logger.error(f"Error al crear donación: {e.details}")
            raise e

    def get_donacion(self, donacion_id):
        """
        Obtiene una donación por su ID.
        :param donacion_id: int
        :return: donacion_pb2.Donacion de la donación encontrada
        """
        request = donacion_pb2.DonacionIdRequest(id=donacion_id)
        try:
            response = self.stub.GetDonacion(request)
            logger.info(f"Donación obtenida: {response.descripcion}")
            return response
        except grpc.RpcError as e:
            if e.code() == grpc.StatusCode.NOT_FOUND:
                logger.warning(f"Donación con ID {donacion_id} no encontrada.")
            else:
                logger.error(f"Error al obtener donación: {e.details}")
            raise e

    def update_donacion(self, categoria, descripcion, cantidad): # El .proto dice DonacionDTO, así que el ID no va aquí
        """
        Actualiza una donación existente.
        :param categoria: str (para el DTO, pero el .proto dice que no se actualiza. Revisar con el equipo)
        :param descripcion: str
        :param cantidad: int
        :return: donacion_pb2.DonacionDTO de la donación actualizada
        """
        # Convierte la categoría string a su valor enum de protobuf
        try:
            pb_categoria = donacion_pb2.DonacionCategoria.Value(categoria.upper())
        except ValueError:
            logger.error(f"Categoría '{categoria}' inválida. Usando ROPA por defecto.")
            pb_categoria = donacion_pb2.DonacionCategoria.ROPA

        donacion_dto = donacion_pb2.DonacionDTO(
            categoria=pb_categoria,
            descripcion=descripcion,
            cantidad=cantidad
        )
        try:
            response = self.stub.UpdateDonacion(donacion_dto)
            logger.info(f"Donación actualizada exitosamente: {response.descripcion}")
            return response
        except grpc.RpcError as e:
            logger.error(f"Error al actualizar donación: {e.details}")
            raise e

    def delete_donacion(self, donacion_id):
        """
        Marca una donación como eliminada (baja lógica).
        :param donacion_id: int
        :return: google.protobuf.empty_pb2.Empty
        """
        request = donacion_pb2.DonacionIdRequest(id=donacion_id)
        try:
            response = self.stub.DeleteDonacion(request)
            logger.info(f"Donación con ID {donacion_id} marcada como eliminada.")
            return response
        except grpc.RpcError as e:
            logger.error(f"Error al eliminar donación: {e.details}")
            raise e

    def list_donaciones(self):
        """
        Lista todas las donaciones.
        :return: donacion_pb2.DonacionListResponse con la lista de donaciones
        """
        try:
            response = self.stub.ListDonaciones(donacion_pb2.Empty())
            logger.info(f"Listadas {len(response.donaciones)} donaciones.")
            return response
        except grpc.RpcError as e:
            logger.error(f"Error al listar donaciones: {e.details}")
            raise e

# --- Ejemplo de uso (solo para pruebas locales) ---
if __name__ == '__main__':
    client = DonacionesClient()

    # Ejemplo: Crear una donación
    try:
        new_donacion_dto = client.create_donacion("ROPA", "Remeras varias", 50)
        print(f"Creada: {new_donacion_dto}")
    except grpc.RpcError:
        pass # Manejado por el logger

    # Ejemplo: Listar donaciones
    try:
        donaciones_list = client.list_donaciones()
        for donacion in donaciones_list.donaciones:
            print(f"Donación: ID={donacion.id}, Desc={donacion.descripcion}, Cant={donacion.cantidad}")
    except grpc.RpcError:
        pass

    # Ejemplo: Obtener una donación específica (asumiendo que hay una con ID 1)
    try:
        donacion_by_id = client.get_donacion(1)
        print(f"Obtenida por ID: {donacion_by_id.descripcion}")
    except grpc.RpcError:
        pass

    # Ejemplo: Actualizar una donación (si sabes su ID y tienes los campos modificables)
    # Nota: El UpdateDonacion espera un DonacionDTO en el .proto.
    # Si quieres actualizar por ID, el .proto debería tener un mensaje específico con ID.
    # Por ahora, este ejemplo NO incluye el ID, ya que el DTO del .proto no lo tiene.
    # Necesitarías un DonacionUpdateRequest = message { int64 id = 1; DonacionDTO donacion = 2; }
    # o un DonacionDTO que incluya el ID.
    # Revisar con el equipo si el UpdateDonacion en el servidor usa el DTO para identificar cuál actualizar.
    try:
        # Esto es un ejemplo, no sabemos el ID aquí
        updated_donacion_dto = client.update_donacion("ALIMENTOS", "Latas de atún", 100)
        print(f"Actualizada: {updated_donacion_dto}")
    except grpc.RpcError:
        pass

    # Ejemplo: Eliminar una donación (baja lógica)
    try:
        client.delete_donacion(1) # Asumiendo ID 1
    except grpc.RpcError:
        pass
