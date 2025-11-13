import requests
import json
from datetime import datetime
from typing import Dict, Any

# URL del endpoint REST de Spring Boot (Controller que hicimos en Java)
SPRING_BOOT_API_URL = "http://localhost:8080/api/comentarios/enviar"

# *** ¡ELIMINAR ESTAS LÍNEAS! NO DEBEN ESTAR EN UN MÓDULO GRPc ***
# from flask import Blueprint
# comentario_evento_solidario_bp = Blueprint('comentario_evento', __name__)
# @comentario_evento_solidario_bp.route('/comentarioEvento', methods=['POST'])
# ********************************************************************

def enviar_comentario_a_kafka(datos_del_formulario_grpc) -> Dict[str, Any]:
    """
    Recibe los datos del formulario de React (vía gRPC) y realiza una 
    petición POST al Controller REST de Spring Boot para producir el mensaje en Kafka.
    """
    
    # 1. Preparar el PAYLOAD JSON para ComentarioEventoDTO (Java)
    try:
        # La lógica de payload es correcta
        payload = {
            "idEventoExterno": datos_del_formulario_grpc.idEventoExterno, 
            "idOrganizacion": datos_del_formulario_grpc.idOrganizacion,
            "idVoluntario": datos_del_formulario_grpc.idVoluntario,
            "nombreVoluntario": datos_del_formulario_grpc.nombreVoluntario,
            "apellidoVoluntario": datos_del_formulario_grpc.apellidoVoluntario,
            "comentario": datos_del_formulario_grpc.comentario,
            "fechaHora": datetime.now().isoformat(timespec='milliseconds')
        }
    except AttributeError as e:
        return {"status": "ERROR", "message": f"Error de atributos gRPC: {e}. Conflicto de nombres."}

    # 2. Enviar la solicitud HTTP POST a Spring Boot
    try:
        response = requests.post(
            SPRING_BOOT_API_URL, 
            data=json.dumps(payload), 
            headers={'Content-Type': 'application/json'}
        )
        
        # 3. Procesar la respuesta
        if response.status_code == 200:
            print("POST EXITOSO: Comentario enviado a Spring Boot (200 OK).")
            return {"status": "OK", "message": "Comentario enviado con éxito a Kafka."}
        else:
            return {"status": "ERROR", "message": f"Fallo en el servidor Java: {response.status_code} - {response.text}"}
            
    except requests.exceptions.RequestException as e:
        return {"status": "ERROR", "message": f"No se pudo conectar con el backend Java en {SPRING_BOOT_API_URL}"}