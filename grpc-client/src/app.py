from flask import Flask
from flask_cors import CORS
from Controllers.UsuarioController import usuario_bp
from Controllers.LoginController import login_bp

app = Flask(__name__)
CORS(app)  # Esto habilita CORS para todas las rutas

baseUrl = "/api/client"

#Blueprints
app.register_blueprint(usuario_bp, url_prefix = baseUrl + '/usuarios')
app.register_blueprint(login_bp, url_prefix = baseUrl + '/login')

if __name__ == '__main__':
    app.run(debug=True, port=5000)