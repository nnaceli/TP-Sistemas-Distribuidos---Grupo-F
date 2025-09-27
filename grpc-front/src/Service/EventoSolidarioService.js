import EventoSolidario from '../Models/EventoSolidario';

const BASE_URL = 'http://127.0.0.1:5000/api/client/eventos';


const getAuthHeaders = () => {
    const token = JSON.parse(localStorage.getItem('userSession'))?.token;
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };
};

export const listarEventos = async () => {
    try {
        const response = await fetch(`${BASE_URL}/listar`, {
            method: 'GET',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            throw new Error('Error al obtener eventos');
        }

        const data = await response.json();

        // Mapea los datos del backend a una instancia de tu clase EventoSolidario
        const eventos = data.map(e => new EventoSolidario(
            e.id,
            e.nombre,
            e.descripcion,
            e.fecha, 
            e.miembros || []
        ));

        return eventos;
    } catch (error) {
        console.error('Error en listarEventos:', error);
        return [];
    }
};

export const crearEvento = async (eventoData) => {
    try {
        const response = await fetch(`${BASE_URL}/crear`, {
            method: 'POST',
            headers: getAuthHeaders(),
            body: JSON.stringify(eventoData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al crear evento');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en crearEvento:', error);
        throw error;
    }
};

export const obtenerEventoPorId = async (id) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'GET',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al obtener evento');
        }

        const data = await response.json();
        
        // Instancia el objeto. 
        return new EventoSolidario(
            data.id,
            data.nombre,
            data.descripcion,
            data.fecha,
            data.miembros || []
        );
    } catch (error) {
        console.error('Error en obtenerEventoPorId:', error);
        throw error;
    }
};

export const eliminarEvento = async (id) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al eliminar evento');
        }

        const data = await response.json();
        return data.mensaje;
    } catch (error) {
        console.error('Error en eliminarEvento:', error);
        throw error;
    }
};


export const actualizarEvento = async (id, eventoData) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PUT',
            headers: getAuthHeaders(),
            body: JSON.stringify(eventoData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al actualizar evento');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en actualizarEvento:', error);
        throw error;
    }
};