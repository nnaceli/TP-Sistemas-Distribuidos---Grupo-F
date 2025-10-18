import AdhesionEvento from '../Models/AdhesionEvento';
import EventoSolidario from '../Models/EventoSolidario';
import EventoSolidarioExterno from '../Models/EventoSolidarioExterno';
import { obtenerUsuarioPorUsername } from './UsuarioService';

const BASE_URL = 'http://127.0.0.1:5000/api/client/eventos';
// A COLA DE MENSAJES
const MESSAGE_QUEUE_URL = 'http://localhost:8085/api/eventos';


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

        const eventoAEliminar = await obtenerEventoPorId(id);

        if (!eventoAEliminar) {
            throw new Error('Evento no encontrado');
        }

        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al eliminar evento');
        }
        
        // convertirlo a modelo.EventoSolidarioExterno
        const notificacionBajaEvento = new EventoSolidarioExterno(
            eventoAEliminar.id,
            eventoAEliminar.nombre,
            eventoAEliminar.descripcion,
            eventoAEliminar.fecha
        );
        //COMUNICAR BAJA DE EVENTO A OTRAS ORGANIZACIONES
        const responseProd= await fetch(`${MESSAGE_QUEUE_URL}/eliminar`, {
            method: 'POST',
            body: JSON.stringify(notificacionBajaEvento )
        });

        const data = await responseProd.json();
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

export const inscribirUsuario = async (eventoId, username) => {
    try {
        const usuario= await obtenerUsuarioPorUsername(username);
        if (!usuario) {
            throw new Error('Usuario no encontrado');
        }

        const miembrosPayload = [ usuario.username ];
        console.log('Payload de miembros:', miembrosPayload);
        const response = await fetch(`${BASE_URL}/inscribirMiembro/${eventoId}`, {
            method: 'PUT',
            headers: getAuthHeaders(),
            body: JSON.stringify({ miembros: miembrosPayload })
        });
        console.log('Respuesta de la inscripcion:', response);
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al inscribir usuario');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error al inscribir usuario:', error);
        throw error;
    }
};

export const exponerEvento = async (id) => {
    try {
        const eventoAExponer = await obtenerEventoPorId(id);

        if (!eventoAExponer) {
            throw new Error('Evento no encontrado');
        }
        console.log("Evento a exponer:", eventoAExponer);
        // convertirlo a modelo.EventoSolidarioExterno
        const notificacionPublicarEvento = new EventoSolidarioExterno(
            eventoAExponer.id,
            eventoAExponer.nombre,
            eventoAExponer.descripcion,
            eventoAExponer.fecha
        );
        console.log("Notificacion a enviar:", notificacionPublicarEvento);
        const response = await fetch(`${MESSAGE_QUEUE_URL}/publicarEvento`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(notificacionPublicarEvento)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al exponer evento');
        }
        const data = await response.json();
        return data.mensaje;
    } catch (error) {
        console.error('Error en exponerEvento:', error);
        throw error;
    }
};

export const listarEventosExternos = async () => {
    try {

        const response = await fetch(`${MESSAGE_QUEUE_URL}/listarEventos`, {
            method: 'GET'
        });
        if (!response.ok) {
            throw new Error('Error al obtener eventos externos');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en listarEventosExternos:', error);
        return [];
    }
};

export const inscribirUsuarioAEventoExterno = async (evento, username) => {
    try {
        const usuario = await obtenerUsuarioPorUsername(username);
        if (!usuario) {
            throw new Error('Usuario no encontrado');
        }

        const adhesionData = AdhesionEvento.fromEventoYUsuario(evento, usuario);
        console.log('Adhesion que se envía:', adhesionData);

        const response = await fetch(`${MESSAGE_QUEUE_URL}/adhesionEvento`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( adhesionData  )
        });
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al inscribir usuario en evento externo');
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error al inscribir usuario en evento externo:', error);
        throw error;
    }
};
