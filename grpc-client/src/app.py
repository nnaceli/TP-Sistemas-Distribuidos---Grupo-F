from flask import Flask
from flask_cors import CORS
from Controllers.UsuarioController import usuario_bp
from Controllers.LoginController import login_bp
from Controllers.DonacionController import donacion_bp
from Controllers.EventoSolidarioController import evento_bp
from Controllers.InformeDonacionesExcelController import informe_excel_donaciones_bp
from Controllers.InformeDonacionesPrototipo import debug_bp # <-- NUEVA IMPORTACIÓN

app = Flask(__name__)
CORS(app)

baseUrl = "/api/client"

# Blueprints
app.register_blueprint(usuario_bp, url_prefix = baseUrl + '/usuarios')
app.register_blueprint(login_bp, url_prefix = baseUrl + '/login')
app.register_blueprint(donacion_bp, url_prefix = baseUrl + '/donacion')
app.register_blueprint(evento_bp, url_prefix = baseUrl + '/eventos')
app.register_blueprint(informe_excel_donaciones_bp, url_prefix = baseUrl + '/reportes_donaciones')

# REGISTRO DEL BLUEPRINT DE DEBUG
app.register_blueprint(debug_bp, url_prefix = baseUrl + '/debug')

if __name__ == '__main__':
    app.run(debug=True, port=5000)