import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarEventos, eliminarEvento } from '../../Service/EventoSolidarioService';
import '../../CSS/EventoSolidarioList.css'; // Asegúrate de crear este archivo CSS

export const EventoSolidarioList = () => {
    const [eventos, setEventos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // 1. Lógica de Carga de Datos
    useEffect(() => {
        const fetchEventos = async () => {
            try {
                const data = await listarEventos(); 
                setEventos(data);
                setLoading(false);
            } catch (err) {
                setError(err.message || 'Error desconocido al cargar eventos.');
                setLoading(false);
            }
        };
        fetchEventos();
    }, []);

    // Función para manejar la eliminación lógica del evento
    const handleEliminar = async (id, nombre) => {
        if (window.confirm(`¿Está seguro de eliminar el evento: "${nombre}"?`)) {
            try {
                await eliminarEvento(id); 
                
                alert(`Evento "${nombre}" (ID: ${id}) eliminado exitosamente.`);
                
                setEventos(prev => prev.filter(e => e.id !== id)); 
            } catch (err) {
                console.error("Error al eliminar el evento:", err);
                setError(`Error al eliminar: ${err.message}`);
            }
        }
    };

    // Función auxiliar para formatear la fecha
    const formatearFecha = (fechaString) => {
        if (!fechaString) return 'N/A';
        try {
            // Asume que la fecha viene en formato ISO o YYYY-MM-DD
            const date = new Date(fechaString);
            // Formato dd/mm/yyyy
            return date.toLocaleDateString('es-AR', { year: 'numeric', month: '2-digit', day: '2-digit' }); 
        } catch (e) {
            return 'Fecha Inválida';
        }
    };


    if (loading) return <p className="loading">Cargando listado de eventos...</p>;
    if (error) return <p className="error">Error: {error}</p>;

    return (
        <div className="evento-list-container">
            <h2>Gestión de Eventos Solidarios</h2>
            
            <button 
                className="btn-crear" 
                onClick={() => navigate('/eventos_solidarios/nuevo')}>
                + Registrar Nuevo Evento
            </button>

            {eventos.length === 0 ? (
                <p>No hay eventos solidarios registrados.</p>
            ) : (
                <table className="evento-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Fecha</th>
                            <th>Miembros Inscritos</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {eventos.map(evento => (
                            <tr key={evento.id}>
                                <td>{evento.id}</td>
                                <td>{evento.nombre}</td>
                                <td>{evento.descripcion.substring(0, 50)}{evento.descripcion.length > 50 ? '...' : ''}</td>
                                <td>{formatearFecha(evento.fecha)}</td>
                                <td>{evento.miembros?.length || 0}</td> {/* Muestra la cantidad de miembros */}
                                <td>
                                    <button 
                                        className="btn-modificar"
                                        onClick={() => navigate(`/eventos_solidarios/editar/${evento.id}`)}>
                                        Modificar
                                    </button>
                                    <button 
                                        className="btn-eliminar"
                                        onClick={() => handleEliminar(evento.id, evento.nombre)}>
                                        Eliminar
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};