import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { obtenerUsuarioPorUsername, eliminarUsuario } from '../../Service/UsuarioService';
import '../../CSS/UsuarioDetalle.css';

export const UsuarioDetalle = () => {
    const { username } = useParams();
    const navigate = useNavigate();
    const [usuario, setUsuario] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const data = await obtenerUsuarioPorUsername(username);
                setUsuario(data);
            } catch (err) {
                setError(err.message);
            }
        };
        fetchUsuario();
    }, [username]);

    if (error) return <p className="error">{error}</p>;
    if (!usuario) return <p className="loading">Cargando usuario...</p>;

    return (
        <div className="usuario-detalle-container">
            <h2>Detalle de Usuario</h2>
            <div className="usuario-detalle-card">
                <p><strong>Nombre:</strong> {usuario.nombre}</p>
                <p><strong>Apellido:</strong> {usuario.apellido}</p>
                <p><strong>Username:</strong> {usuario.username}</p>
                <p><strong>Email:</strong> {usuario.email}</p>
                <p><strong>Teléfono:</strong> {usuario.telefono}</p>
                <p><strong>Rol:</strong> {usuario.rol}</p>
                <p><strong>Estado:</strong> {usuario.activo ? '🟢 Activo' : '🔴 Inactivo'}</p>
            </div>
            <button className="btn-volver" onClick={() => navigate('/usuarios')}>
                Volver
            </button>
            
            <button
    className="btn-eliminar"
    onClick={() => {
        if (window.confirm(`¿Estás seguro que querés eliminar a ${usuario.username}?`)) {
            eliminarUsuario(usuario.username)
                .then(() => navigate('/usuarios'))
                .catch(err => alert(err.message));
        }
    }}>
    Eliminar
</button>
        </div>
    );
};
