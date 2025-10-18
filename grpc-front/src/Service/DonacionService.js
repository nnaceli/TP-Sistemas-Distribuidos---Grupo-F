const BASE_URL = 'http://127.0.0.1:5000/api/client/donacion';
// A COLA DE MENSAJES
const MESSAGE_QUEUE_URL = 'http://127.0.0.1:8085/api/donaciones';

const getAuthHeaders = () => {
    const token = JSON.parse(localStorage.getItem('userSession'))?.token;
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };
};

// Función para listar todas las Donaciones
export const listarDonaciones = async () => {
    try {
        const response = await fetch(`${BASE_URL}/listar`, {
            method: 'GET',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            throw new Error('Error al obtener donaciones');
        }

        const data = await response.json();

        const donaciones = data.map(d => ({
            ...d,
        }));

        return donaciones;
    } catch (error) {
        console.error('Error en listarDonaciones:', error);
        return [];
    }
};

// Función para crear una nueva Donación
export const crearDonacion = async (donacionData) => {
    try {
        // testeo
        const headers = getAuthHeaders();
        console.log("Headers que se envían:", headers);
        const url = `${BASE_URL}/crear`;
        console.log("URL de destino:", url);

        const response = await fetch(`${BASE_URL}/crear`, {
            method: 'POST',
            headers: getAuthHeaders(),
            body: JSON.stringify(donacionData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al crear donación');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en crearDonacion:', error);
        throw error;
    }
};

// Función para obtener una Donación por ID
export const obtenerDonacionPorId = async (id) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'GET',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al obtener donación');
        }

        const data = await response.json();
        
        // Retorna el objeto de donación. 
        return data;
    } catch (error) {
        console.error('Error en obtenerDonacionPorId:', error);
        throw error;
    }
};

// Función para eliminar una Donación
export const eliminarDonacion = async (id) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al eliminar donación');
        }

        const data = await response.json();
        return data.mensaje;
    } catch (error) {
        console.error('Error en eliminarDonacion:', error);
        throw error;
    }
};

// Función para actualizar una Donación
export const actualizarDonacion = async (id, donacionData) => {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PUT',
            headers: getAuthHeaders(),
            body: JSON.stringify(donacionData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al actualizar donación');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en actualizarDonacion:', error);
        throw error;
    }
};

export const solicitarDonaciones = async (solicitudData) => {
    try {
        console.log("Solicitud que se envía:", solicitudData);
        const url = `${MESSAGE_QUEUE_URL}/solicitud`;
        console.log("URL de destino:", url);

        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(solicitudData)
                });
            console.log("Fetch response status:", response.status, response.statusText);

        if (response.status === 204) {
            return { ok: true };
        }

        if (!response.ok) {
            const errorBody = await response.text();
            console.error("Error response body:", errorBody);
            throw new Error(`Error al solicitar donaciones: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        return { ok: true, data };
    } catch (error) {
        console.error('Error al solicitar donaciones:', error);
        throw error;
    }
}

//TODO: habilitar cuando se implemente el endpoint en la cola de mensajes
// export const traerSolicitudesDonaciones = async () => {
//     try {
//         const response = await fetch(`${MESSAGE_QUEUE_URL}/solicitudes`, {
//             method: 'GET',
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         });
//         if (!response.ok) {
//             const error = await response.json();
//             throw new Error(error?.error || 'Error al traer solicitudes de donaciones');
//         }
//         const data = await response.json();
//         return data;
//     } catch (error) {
//         console.error('Error en traerSolicitudesDonaciones:', error);
//         throw error;
//     }   
// };

//TODO: a partir de la vista de solicitudes, un boton de transferir donaciones
