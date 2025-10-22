from flask import Blueprint, request, jsonify, send_file, Response, Flask
from openpyxl import Workbook
import io
from grpc import RpcError
from GrpcService.GrpcDonacionService import listar_las_donaciones

def extraer_token():
    auth_header = request.headers.get("Authorization")
    if not auth_header or not auth_header.startswith("Bearer "):
        return None
    return auth_header.split(" ")[1]

app = Flask(__name__)
informe_excel_donaciones_bp = Blueprint('informes_donaciones', __name__)

@informe_excel_donaciones_bp.route('/excel', methods=['GET'])
def generar_informe_excel():
    try:
        ##token = extraer_token()
        #if not token:
            #return jsonify({"error": "Token no proporcionado"}), 401
        
        #donaciones = listar_las_donaciones(token)
        #wb = Workbook()
        #for d in donaciones:
        #------------------------------------------------------------

        wb = Workbook()
    
        ws = wb.active
        ws.title = "Prototipo Donaciones"
        
        # Encabezados
        ws.append(['Fecha', 'Categoria', 'Cantidad']) 
        
        # Datos de prueba (una donación simulada)
        ws.append(['2025-10-21', 'ALIMENTOS', 150])
        output = io.BytesIO()

        wb.save(output)
        output.seek(0)
        filename = "Informe_Donaciones_Prototipo.xlsx" 
        
        return Response(
            output,
            mimetype="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            headers={"Content-Disposition": f"attachment;filename={filename}"}
        )

    except RpcError as e:
        mensaje = e.details() if e.details() else "Error en la generacion del reporte"
        return jsonify({"error": mensaje}), 404

        
if __name__ == '__main__':
    # Ejecuta el servidor en el puerto 5000 (o el que uses para tu grpc-client REST)
    app.run(port=5000, debug=True)

