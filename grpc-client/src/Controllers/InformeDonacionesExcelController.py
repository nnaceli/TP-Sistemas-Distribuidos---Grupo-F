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

informe_excel_donaciones_bp = Blueprint('informes_donaciones', __name__)

@informe_excel_donaciones_bp.route('/excel', methods=['GET'])
def generar_informe_excel():
    try:
        token = extraer_token()
        if not token:
            return jsonify({"error": "Token no proporcionado"}), 401
        
        donaciones = listar_las_donaciones(token)

        # agrupacion de donaciones por categorias
        donaciones_agrupadas = {
            'ROPA': [],
            'ALIMENTOS': [],
            'JUGUETES': [],
            'UTILES ESCOLARES': []
        }
        
        for donacion in donaciones:
            categoria = donacion.categoria.upper()
            if categoria in donaciones_agrupadas:
                donaciones_agrupadas[categoria].append(donacion)
        
        #creacion de archivo excel y escritura de datos
        wb = Workbook()
        
            # se elimina hoja creada por defecto
        if 'Sheet' in wb.sheetnames:
            del wb['Sheet']

        headers = ['Fecha de Alta', 'Descripción', 'Cantidad', 'Eliminado', 
                   'Usuario Alta', 'Usuario Modificación']

        for categoria, lista_donaciones in donaciones_agrupadas.items():
                
            # Crea la hoja con el nombre de la Categoría
            ws = wb.create_sheet(title=categoria)
            ws.append(headers) 
            
            # Vuelca los datos de las donaciones en esa hoja
            for donacion in lista_donaciones:
                ws.append([
                    donacion.fechaAlta, 
                    donacion.descripcion, 
                    donacion.cantidad, 
                    donacion.eliminado, 
                    donacion.fechaModificacion, 
                    donacion.usuarioModificacion,
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

    except RpcError as e:
        mensaje = e.details() if e.details() else "Error en la generacion del reporte"
        return jsonify({"error": mensaje}), 404

        
if __name__ == '__main__':
    # Ejecuta el servidor en el puerto 5000 (o el que uses para tu grpc-client REST)
    app.run(port=5000, debug=True)

