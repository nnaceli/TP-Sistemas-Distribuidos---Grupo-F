from flask import Flask
from Controllers.UsuarioController import usuario_bp

app = Flask(__name__)

baseUrl = "/api/client"

#Blueprints
app.register_blueprint(usuario_bp, url_prefix = baseUrl + '/usuarios')

if __name__ == '__main__':
    app.run(debug=True, port=5000)