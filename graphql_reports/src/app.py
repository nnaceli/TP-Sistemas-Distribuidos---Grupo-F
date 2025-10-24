import database
import os
from dotenv import load_dotenv
from flask import Flask, jsonify, request
from flask_cors import CORS
from ariadne import ObjectType, graphql_sync, load_schema_from_path, make_executable_schema
from queries import donacion_recibida_query as recibidas 
from queries.donacion_transferida_query import resolve_donaciones_transferidas
from queries.donacion_recibida_query import resolve_donaciones_recibidas
from ariadne.explorer import ExplorerGraphiQL

#set database
app = Flask(__name__)
load_dotenv()
DATABASE_URL = os.getenv("DATABASE_URL")
app.config["SQLALCHEMY_DATABASE_URI"] = DATABASE_URL
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
database.db.init_app(app)

# 1. Carga el schema (esto no cambia)
type_defs = load_schema_from_path("schema.gql")

all_query = ObjectType("Query")
all_query.set_field("listarDonacionesRecibidas", resolve_donaciones_recibidas)
all_query.set_field("listarDonacionesTransferidas", resolve_donaciones_transferidas)

# 4. Une el schema y los resolvers (esto no cambia)
schema = make_executable_schema(type_defs, all_query)

# 6. AÑADE EL MIDDLEWARE DE CORS (versión Flask)
CORS(app, resources={
    r"/graphql*": {
        "origins": ["http://localhost:3000"]
    }
})
@app.route("/graphql", methods=["GET"])
def graphql_playground():
    return ExplorerGraphiQL().html(None), 200


@app.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(
        schema,
        data,
        context_value=request,
        debug=app.debug
    )
    status_code = 200 if success else 400
    return jsonify(result), status_code

# (Opcional) Una ruta de bienvenida
@app.route("/")
def index():
    return "Servidor WSGI (Flask) con GraphQL funcionando. Ve a /graphql"

if __name__ == "__main__":
    app.run(debug=True, port=5002)



