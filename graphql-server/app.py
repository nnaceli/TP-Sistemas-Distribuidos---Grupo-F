from flask import Flask
from controllers.donacion_controller import donacion_bp
from controllers.evento_controller import evento_bp


app = Flask(__name__)
app.register_blueprint(donacion_bp)
app.register_blueprint(evento_bp)

if __name__ == "__main__":
    app.run(debug=True, port=5000)
