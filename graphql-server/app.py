from flask import Flask
from flask_cors import CORS
from controllers.donacion_controller import donacion_bp
from controllers.evento_controller import evento_bp


app = Flask(__name__)
CORS(app)
app.register_blueprint(donacion_bp)
app.register_blueprint(evento_bp)

if __name__ == "__main__":
    app.run(debug=True, port=5005)
