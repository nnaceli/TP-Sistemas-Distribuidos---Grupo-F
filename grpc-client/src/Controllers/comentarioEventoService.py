import requests
import json
from datetime import datetime

# URL del endpoint REST de Spring Boot (Controller que hicimos en Java)
SPRING_BOOT_API_URL = "http://localhost:8080/api/comentarios/enviar"

def enviar_comentario_a_kafka(datos_del_formulario_grpc):
    """
    Recibe los datos del formulario de React (vía gRPC) y los envía 
    al Controller REST de Spring Boot, que actúa como Productor de Kafka.
    """
    
    # 1. Preparar el PAYLOAD JSON para que coincida con ComentarioEventoDTO (Java)
    try:
        
        payload = {
            # Los nombres de los atributos aquí dependen de cómo los envía tu stub gRPC
            "idEventoExterno": datos_del_formulario_grpc.idEventoExterno, 
            "idOrganizacion": datos_del_formulario_grpc.idOrganizacion,
            "idVoluntario": datos_del_formulario_grpc.idVoluntario,
            "nombreVoluntario": datos_del_formulario_grpc.nombreVoluntario,
            "apellidoVoluntario": datos_del_formulario_grpc.apellidoVoluntario,
            "comentario": datos_del_formulario_grpc.comentario,
            
            # Generamos la fecha actual en formato ISO 8601, necesario para LocalDateTime en Java.
            "fechaHora": datetime.now().isoformat(timespec='milliseconds')
        }
    except AttributeError as e:
        print(f"ERROR: Los atributos gRPC no coinciden con la estructura esperada: {e}")
        return {"status": "ERROR", "message": "Datos de formulario gRPC inválidos."}

    # 2. Enviar la solicitud HTTP POST a Spring Boot
    try:
        response = requests.post(
            SPRING_BOOT_API_URL, 
            data=json.dumps(payload), 
            headers={'Content-Type': 'application/json'}
        )
        
        # 3. Procesar la respuesta del Controller de Java
        if response.status_code == 200:
            print("Comentario enviado a Spring Boot (200 OK). Procesamiento asíncrono iniciado.")
            return {"status": "OK", "message": "Comentario enviado con éxito a Kafka."}
        else:
            print(f"Error en la API de Java (Código {response.status_code}): {response.text}")
            return {"status": "ERROR", "message": f"Fallo en el servidor Java: {response.status_code}"}
            
    except requests.exceptions.RequestException as e:
        print(f"Error de conexión con la API REST de Java: {e}")
        return {"status": "ERROR", "message": f"No se pudo conectar con el backend Java en {SPRING_BOOT_API_URL}"}