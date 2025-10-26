from flask import Blueprint, request, jsonify, Response
from openpyxl import Workbook
import io
import requests
from grpc import RpcError 

JAVA_REST_BASE_URL = "http://localhost:8081/api/rest"


def listar_las_donaciones_completas():
    url = "http://localhost:8081/api/rest/donaciones/traer"
    try:
        response = requests.get(url)
        response.raise_for_status()  # Lanza excepción si el status no es 2xx
        data = response.json()       # Asigna el contenido JSON a 'data'
        return data
    except requests.exceptions.RequestException as e:
        print(f"Error al consumir el servicio: {e}")
        return None


def extraer_token():
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return None
    return auth_header.split(" ")[1]

informe_excel_donaciones_bp = Blueprint('informes_donaciones', __name__)

@informe_excel_donaciones_bp.route('/excel', methods=['GET'])
def generar_informe_excel():
    try:
        donaciones = listar_las_donaciones_completas()

        # agrupacion de donaciones por categorias
        donaciones_agrupadas = {
            'ROPA': [],
            'ALIMENTOS': [],
            'JUGUETES': [],
            'UTILES_ESCOLARES': []
        }
        
        for donacion in donaciones:
            donaciones_agrupadas[donacion['categoria']].append(donacion)

        #creacion de archivo excel y escritura de datos
        wb = Workbook()
        
        # se elimina hoja creada por defecto
        if 'Sheet' in wb.sheetnames:
            del wb['Sheet']

        headers = ['Fecha de Alta', 'Descripción', 'Cantidad', 'Eliminado', 
                   'Usuario Alta', 'Usuario Modificación'] # Corregidos los headers para incluir los nuevos campos

        for categoria, lista_donaciones in donaciones_agrupadas.items():
            
            # Crea la hoja con el nombre de la Categoría
            ws = wb.create_sheet(title=categoria)
            ws.append(headers) 
            
            # Vuelca los datos de las donaciones en esa hoja
            for donacion in lista_donaciones:
                ws.append([
                    donacion['fechaCreacion'], 
                    donacion['descripcion'], 
                    donacion['cantidad'], 
                    donacion['eliminado'], 
                    donacion['usernameCreacion'], 
                    donacion['usernameModificacion'],
                ])
                
        # se guarda el archivo y se envía para descargar
        output = io.BytesIO()
        wb.save(output)
        output.seek(0)
        filename = "Informe_Donaciones.xlsx"
        
        return Response(
            output,
            mimetype="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            headers={"Content-Disposition": f"attachment;filename={filename}"}
        )

    except requests.exceptions.RequestException as e:
        # Captura errores de conexión o HTTP (4xx, 5xx) del servidor Java
        error_msg = f"Error de comunicación con el servidor Java ({JAVA_REST_BASE_URL}): {str(e)}"
        return jsonify({"error": error_msg}), 503 # Servicio no disponible
    
    except Exception as e:
        # Otros errores (por ejemplo, procesamiento del Excel)
        return jsonify({"error": f"Error interno al generar el informe: {str(e)}"}), 500

# Esta parte solo se usa si ejecutas este archivo directamente como servidor de pruebas.
if __name__ == '__main__':
    from flask import Flask
    app = Flask(__name__)
    # Se utiliza un prefijo de URL para la ruta
    app.register_blueprint(informe_excel_donaciones_bp, url_prefix='/api/informes/donaciones') 
    app.run(port=5000, debug=True)
