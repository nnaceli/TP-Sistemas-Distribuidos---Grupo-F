from flask import Flask
from flask_cors import CORS
from controllers.soap_client_controller import soapclientBp

app = Flask(__name__)
CORS(app)  # Esto habilita CORS para todas las rutas

baseUrl = "/api/soap-client"

#Blueprints
app.register_blueprint(soapclientBp, url_prefix = baseUrl + '/info')

if __name__ == '__main__':
    app.run(debug=True, port=8888)