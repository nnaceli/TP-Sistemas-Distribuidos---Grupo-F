import { UserSession } from '../Models/UserSession'

const BASE_URL = 'http://127.0.0.1:5000/api/client/login';

export const loginUsuario = async (username, password) => {
    try {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error en el login');
        }

        const data = await response.json();
        const userSession = new UserSession(data.token, data.username, data.rol);
        console.log(userSession);
        return userSession;
    } catch (error) {
        console.error('Error en loginUsuario:', error);
        throw error;
    }
};
