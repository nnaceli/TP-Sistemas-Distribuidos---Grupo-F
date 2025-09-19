import React, { useState } from 'react';
import { Roles } from '../../Models/Roles';
import { crearUsuario } from '../../Service/UsuarioService';
import '../../CSS/UsuarioForm.css';
import { useNavigate } from 'react-router-dom';

export const UsuarioForm = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        username: '',
        nombre: '',
        apellido: '',
        telefono: '',
        email: '',
        rol: Roles[0],
        activo: false
    });

    const handleChange = e => {
        const { name, value, type, checked } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSubmit = async e => {
        e.preventDefault();

        const payload = {
            username: formData.username,
            nombre: formData.nombre,
            apellido: formData.apellido,
            telefono: formData.telefono,
            email: formData.email,
            rol: formData.rol
        };

        try {
            const resultado = await crearUsuario(payload);
            console.log('Usuario creado:', resultado);

            // Limpiar formulario
            setFormData({
                username: '',
                nombre: '',
                apellido: '',
                telefono: '',
                email: '',
                rol: Roles[0],
                activo: false
            });

            // Redirigir al listado
            navigate('/usuarios');
        } catch (error) {
            alert(error.message);
        }
    };

    return (
        <div>
        <form className="formulario-usuario" onSubmit={handleSubmit}>
            <h3>Nuevo Usuario</h3>

            <input
                type="text"
                name="username"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
                required
            />
            <input
                type="text"
                name="nombre"
                placeholder="Nombre"
                value={formData.nombre}
                onChange={handleChange}
                required
            />
            <input
                type="text"
                name="apellido"
                placeholder="Apellido"
                value={formData.apellido}
                onChange={handleChange}
                required
            />
            <input
                type="text"
                name="telefono"
                placeholder="Teléfono"
                value={formData.telefono}
                onChange={handleChange}
                required
            />
            <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
            />

            <label>
                Rol:
                <select name="rol" value={formData.rol} onChange={handleChange} required>
                    {Roles.map((rol, index) => (
                        <option key={index} value={rol}>
                            {rol}
                        </option>
                    ))}
                </select>
            </label>

            <label className="checkbox-label">
                <input
                    type="checkbox"
                    name="activo"
                    checked={formData.activo}
                    onChange={handleChange}
                />
                Activo
            </label>

            <button type="submit">Crear Usuario</button>
        </form>
          <button className="btn-volver" onClick={() => navigate('/usuarios')}>
                Volver
            </button>
        </div>
    );
};
