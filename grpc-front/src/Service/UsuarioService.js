import Usuario from '../Models/Usuario';

const BASE_URL = 'http://127.0.0.1:5000/api/client/usuarios';

export const listarUsuarios = async () => {
    try {
        const response = await fetch(`${BASE_URL}/listar`);
        if (!response.ok) {
            throw new Error('Error al obtener usuarios');
        }

        const data = await response.json();

        // Convertimos cada objeto plano en una instancia de Usuario
        const usuarios = data.map(u => new Usuario(
            u.id,
            u.username,
            u.nombre,
            u.apellido,
            u.telefono,
            null,
            u.email,
            u.rol,
            u.activo
        ));

        return usuarios;
    } catch (error) {
        console.error('Error en listarUsuarios:', error);
        return [];
    }
};

export const crearUsuario = async (usuarioData) => {
    try {
        const response = await fetch(`${BASE_URL}/crear`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuarioData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al crear usuario');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en crearUsuario:', error);
        throw error;
    }
};

export const obtenerUsuarioPorUsername = async (username) => {
    try {
        const response = await fetch(`${BASE_URL}/${username}`);
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al obtener usuario');
        }

        const data = await response.json();

        const usuario = new Usuario(
            data.id,
            data.username,
            data.nombre,
            data.apellido,
            data.telefono,
            '', // password no viene en el response
            data.email,
            data.rol,
            data.activo
        );

        return usuario;
    } catch (error) {
        console.error('Error en obtenerUsuarioPorUsername:', error);
        throw error;
    }
};

export const eliminarUsuario = async (username) => {
    try {
        const response = await fetch(`${BASE_URL}/${username}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al eliminar usuario');
        }

        const data = await response.json();
        return data.mensaje;
    } catch (error) {
        console.error('Error en eliminarUsuario:', error);
        throw error;
    }
};

export const actualizarUsuario = async (username, usuarioData) => {
    try {
        const response = await fetch(`${BASE_URL}/${username}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuarioData)
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error?.error || 'Error al actualizar usuario');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error en actualizarUsuario:', error);
        throw error;
    }
};
