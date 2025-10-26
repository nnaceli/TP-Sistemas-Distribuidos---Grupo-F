from flask import Blueprint, request, jsonify
from client.client import get_associations, get_presidents

soapclientBp = Blueprint('soapclientBp', __name__)

@soapclientBp.route('/associations', methods=['POST'])
def associations():
    data = request.get_json()
    ids = data.get('ids', [])
    result = get_associations(ids)
    return jsonify([assoc.to_dict() for assoc in result])

@soapclientBp.route('/presidents', methods=['POST'])
def presidents():
    data = request.get_json()
    ids = data.get('ids', [])
    result = get_presidents(ids)
    return jsonify([pres.to_dict() for pres in result])