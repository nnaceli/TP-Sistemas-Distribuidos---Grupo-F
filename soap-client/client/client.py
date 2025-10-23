from zeep import Client
from zeep import xsd
from client.models.Association import Association
from client.models.President import President


# URL del WSDL
wsdl_url = 'https://soap-app-latest.onrender.com/?wsdl'

# Crear el cliente SOAP
client = Client(wsdl=wsdl_url)

# Crear el header SOAP personalizado
auth_header = xsd.Element(
    '{auth.headers}Auth',
    xsd.ComplexType([
        xsd.Element('{auth.headers}Grupo', xsd.String()),
        xsd.Element('{auth.headers}Clave', xsd.String()),
    ])
)

# Definimos los valores del header
header_value = auth_header(Grupo='GrupoA-TM', Clave='clave-tm-a')


def get_associations(org_ids_list):
    org_ids = {'string': [str(i) for i in org_ids_list]}
    try:
        response = client.service.list_associations(org_ids=org_ids, _soapheaders=[header_value])
        if response is None:
            return []
        associations = []
        for item in response:
            assoc = Association(
                id=int(item['id']),
                name=item['name'],
                address=item['address'],
                phone=item['phone']
            )
            associations.append(assoc)
        return associations
    except Exception as e:
        print(f"Error en list_associations: {e}")
        return []


def get_presidents(org_ids_list):
    org_ids = {'string': [str(i) for i in org_ids_list]}
    try:
        response = client.service.list_presidents(org_ids=org_ids, _soapheaders=[header_value])
        if response is None:
            return []
        presidents = []
        for item in response:
            pres = President(
                id=int(item['id']),
                name=item['name'],
                address=item['address'],
                phone=item['phone'],
                organization_id=int(item['organization_id'])
            )
            presidents.append(pres)
        return presidents
    except Exception as e:
        print(f"Error en list_presidents: {e}")
        return 