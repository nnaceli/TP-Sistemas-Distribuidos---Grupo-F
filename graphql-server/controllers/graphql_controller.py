from flask import Blueprint, request, jsonify
from ariadne import graphql_sync
from ariadne.explorer import ExplorerGraphiQL
from graphql_schemas.schema import schema

graphql_bp = Blueprint("graphql", __name__)
explorer_html = ExplorerGraphiQL().html(None)

@graphql_bp.route("/graphql", methods=["GET"])
def graphql_playground():
    return explorer_html, 200

@graphql_bp.route("/graphql", methods=["POST"])
def graphql_server():
    data = request.get_json()
    success, result = graphql_sync(schema, data, context_value=request, debug=True)
    status_code = 200 if success else 400
    return jsonify(result), status_code