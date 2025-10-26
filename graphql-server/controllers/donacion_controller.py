from flask import Blueprint, request, jsonify
from ariadne import graphql_sync
from ariadne.explorer import ExplorerGraphiQL
from graphql_schemas.donacion_schema import schema

donacion_bp = Blueprint("donacion", __name__)
explorer_html = ExplorerGraphiQL().html(None)

@donacion_bp.route("/graphql", methods=["GET"])
def graphql_playground():
    return explorer_html, 200

@donacion_bp.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code