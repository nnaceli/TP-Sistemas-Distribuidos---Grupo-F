import '../../CSS/UsuarioList.css';
import { useNavigate } from 'react-router-dom';
import { listarUsuarios } from "../../Service/UsuarioService";
import React, { useEffect, useState } from 'react';

export const ListaUsuarios = () => {
    const navigate = useNavigate();
    const [usuarios, setUsuarios] = useState([]);

    useEffect(() => {
        const fetchUsuarios = async () => {
            const data = await listarUsuarios();
            setUsuarios(data);
        };
        fetchUsuarios();
    }, []);

    return (
        <div className="tabla-usuarios-container">
            <h2>Listado de Usuarios</h2>
            <button className="btn-crear-usuario" onClick={() => navigate('/usuarios/nuevo')}>
                Crear
            </button>
            <div className="table-responsive">
                <table className="tabla-usuarios">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Teléfono</th>
                            <th>Email</th>
                            <th>Rol</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usuarios.map(usuario => (
                            <tr key={usuario.id} className={usuario.activo ? 'activo' : 'inactivo'}>
                                <td>{usuario.id}</td>
                                <td>{usuario.username}</td>
                                <td>{usuario.nombre}</td>
                                <td>{usuario.apellido}</td>
                                <td>{usuario.telefono}</td>
                                <td>{usuario.email}</td>
                                <td>
                                    <span className={`rol-badge ${usuario.rol}`}>
                                        {usuario.rol.toUpperCase()}
                                    </span>
                                </td>
                                <td>
                                    <span className={`estado-badge ${usuario.activo ? 'activo' : 'inactivo'}`}>
                                        {usuario.activo ? '🟢 Activo' : '🔴 Inactivo'}
                                    </span>
                                </td>
                                <td>
                                    <button
                                        className="btn-visualizar"
                                        onClick={() => navigate(`/usuarios/${usuario.username}`)}
                                    >
                                        Detalles
                                    </button>
                                    <button
                                        className="btn-editar"
                                        onClick={() => navigate(`/usuarios/${usuario.username}/editar`)}
                                    >
                                        Editar
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};
