from flask import Blueprint, request, jsonify, make_response
import requests
import json

# URL base del servidor REST de Java (informe-api-server)
JAVA_REST_BASE_URL = "http://localhost:8081" # Puerto 8081

debug_bp = Blueprint('debug_donaciones', __name__)

# Función de extracción de token COMENTADA para la prueba sin seguridad
# def extraer_token():
#     auth_header = request.headers.get("Authorization")
#     if not auth_header or not auth_header.startswith("Bearer "):
#         return None
#     return auth_header.split(" ")[1]

@debug_bp.route('/listado', methods=['GET'])
def listar_donaciones_json():
    """
    Endpoint de prueba para llamar al servicio REST de Java y devolver el JSON.
    URL de prueba: http://localhost:5000/api/client/debug/listado
    """
    try:
        # **CAMBIO CLAVE: Ajustamos el endpoint a la ruta simple /donaciones**
        endpoint = f"{JAVA_REST_BASE_URL}/donaciones" 
        
        headers = {
            "Content-Type": "application/json"
        }
        
        # Llama al servidor Java
        response = requests.get(endpoint, headers=headers)
        
        # Verifica si la respuesta HTTP es exitosa (200, 201, etc.)
        response.raise_for_status() 

        # Devuelve el JSON tal cual lo envía el servidor Java
        return jsonify(response.json()), 200

    except requests.exceptions.HTTPError as e:
        # Captura errores como 404, 500, etc., del servidor Java
        status_code = e.response.status_code
        try:
            # Intenta parsear el mensaje de error de Java si está en JSON
            error_details = e.response.json()
        except json.JSONDecodeError:
            # Si no es JSON, usa el texto del error
            error_details = {"mensaje": e.response.text}
            
        return jsonify({"error_origen_java": error_details, "status_code_java": status_code}), status_code

    except requests.exceptions.RequestException as e:
        # Captura errores de conexión (servidor Java apagado o URL incorrecta)
        return jsonify({"error": f"Error de conexión: El servidor Java ({JAVA_REST_BASE_URL}) no responde. {str(e)}"}), 503 
    
    except Exception as e:
        return jsonify({"error": f"Error interno en Python: {str(e)}"}), 500