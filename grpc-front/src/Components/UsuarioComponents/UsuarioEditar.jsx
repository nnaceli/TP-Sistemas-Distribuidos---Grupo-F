import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { obtenerUsuarioPorUsername, actualizarUsuario } from '../../Service/UsuarioService';
import { Roles } from '../../Models/Roles';
import '../../CSS/UsuarioForm.css';

export const UsuarioEditar = () => {
    const { username } = useParams();
    const navigate = useNavigate();
    const [formData, setFormData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUsuario = async () => {
            try {
                const usuario = await obtenerUsuarioPorUsername(username);
                setFormData({
                    nombre: usuario.nombre,
                    apellido: usuario.apellido,
                    telefono: usuario.telefono,
                    email: usuario.email,
                    rol: usuario.rol
                });
            } catch (err) {
                setError(err.message);
            }
        };
        fetchUsuario();
    }, [username]);

    const handleChange = e => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            await actualizarUsuario(username, formData);
            navigate('/');
        } catch (err) {
            alert(err.message);
        }
    };

    if (error) return <p className="error">{error}</p>;
    if (!formData) return <p className="loading">Cargando datos...</p>;

    return (
        <form className="formulario-usuario" onSubmit={handleSubmit}>
            <h3>Editar Usuario</h3>

            <input type="text" name="nombre" placeholder="Nombre" value={formData.nombre} onChange={handleChange} required />
            <input type="text" name="apellido" placeholder="Apellido" value={formData.apellido} onChange={handleChange} required />
            <input type="text" name="telefono" placeholder="Teléfono" value={formData.telefono} onChange={handleChange} required />
            <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />

            <label>
                Rol:
                <select name="rol" value={formData.rol} onChange={handleChange} required>
                    {Roles.map((rol, index) => (
                        <option key={index} value={rol}>{rol}</option>
                    ))}
                </select>
            </label>

            <button type="submit">Guardar Cambios</button>
            <button type="button" className="btn-volver" onClick={() => navigate('/usuarios')}>Cancelar</button>
        </form>
    );
};
