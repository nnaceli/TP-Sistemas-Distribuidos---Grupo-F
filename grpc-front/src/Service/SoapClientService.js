import { Association } from '../Models/SoapClientAssociation.js';
import { President } from '../Models/SoapClientPresident.js';


const BASE_URL = 'http://127.0.0.1:8888/api/soap-client/info';

export const getAssociations = async (ids) => {
  try {
    const response = await fetch(`${BASE_URL}/associations`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ ids }),
    });

    if (!response.ok) {
      throw new Error('Error al obtener asociaciones');
    }

    const data = await response.json();

    return data.map(item => new Association(item.id, item.name, item.address, item.phone));

  } catch (error) {
    console.error('Error en getAssociations:', error);
    return [];
  }
};

export const getPresidents = async (ids) => {
  try {
    const response = await fetch(`${BASE_URL}/presidents`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ ids }),
    });

    if (!response.ok) {
      throw new Error('Error al obtener presidentes');
    }

    const data = await response.json();

    // Convertir cada objeto JSON en una instancia de President
    return data.map(item => new President(item.id, item.name, item.address, item.phone, item.organization_id));
  } catch (error) {
    console.error('Error en getPresidents:', error);
    return [];
  }
};