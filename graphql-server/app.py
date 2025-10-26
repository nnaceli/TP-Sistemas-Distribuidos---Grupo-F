from flask import Flask
from controllers.graphql_controller import graphql_bp

app = Flask(__name__)
app.register_blueprint(graphql_bp)

if __name__ == "__main__":
    app.run(debug=True, port=5000)
