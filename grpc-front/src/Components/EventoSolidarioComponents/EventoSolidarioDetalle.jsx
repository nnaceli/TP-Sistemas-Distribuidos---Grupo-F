import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { obtenerEventoPorId, eliminarEvento } from '../../Service/EventoSolidarioService';
import '../../CSS/EventoSolidarioDetalle.css';

export const EventoSolidarioDetalle = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [evento, setEvento] = useState(null);
    const [error, setError] = useState(null);
    const [isFuturo, setIsFuturo] = useState(false);
    const [miembrosAsignados, setMiembrosAsignados] = useState([]); // Array de usuarios (miembros)

    useEffect(() => {
        const fetchEvento = async () => {
            try {
                const data = await obtenerEventoPorId(id);
                setEvento(data);
                setIsFuturo(new Date(data.fecha) > new Date());
                setMiembrosAsignados(data.miembros || []); // Asume que el backend devuelve la lista de usuarios
            } catch (err) {
                setError(err.message);
            }
        };
        fetchEvento();
    }, [id]);

    const handleEliminar = async () => {
        // La baja es física y solo para eventos a futuro
        if (!isFuturo) {
            alert('¡ERROR! Solo se pueden eliminar eventos a futuro.');
            return;
        }

        if (window.confirm(`¿Estás seguro que quieres ELIMINAR FÍSICAMENTE el evento ID ${id}?`)) {
            try {
                await eliminarEvento(id);
                alert('Evento eliminado FÍSICAMENTE con éxito.');
                navigate('/eventos'); // Vuelve al listado
            } catch (err) {
                alert('Error al eliminar: ' + err.message);
            }
        }
    };
    
    // Función de ejemplo para manejo de miembros (PENDIENTE DE IMPLEMENTACIÓN REAL)
    const handleAsignarMiembro = () => {
        alert('Funcionalidad de Asignar Miembro PENDIENTE (Requiere selector de usuarios activos).');
        // Aquí iría la lógica de PUT al backend para agregar un miembro
    };
    // Función de ejemplo para manejo de miembros (PENDIENTE DE IMPLEMENTACIÓN REAL)
    const handleQuitarMiembro = () => {
        alert('Funcionalidad de Quitar Miembro PENDIENTE.');
        // Aquí iría la lógica de PUT al backend para quitar un miembro
    };

    if (error) return <p className="error">{error}</p>;
    if (!evento) return <p className="loading">Cargando detalle de evento...</p>;

    return (
        <div className="evento-detalle-container">
            <h2>Detalle de Evento ID: {evento.id}</h2>
            <div className={`evento-detalle-card ${isFuturo ? 'futuro' : 'pasado'}`}>
                <p><strong>Estado:</strong> {isFuturo ? '✨ A Futuro' : '✅ Pasado'}</p>
                <p><strong>Nombre:</strong> {evento.nombre}</p>
                <p><strong>Descripción:</strong> {evento.descripcion}</p>
                <p><strong>Fecha y Hora:</strong> {new Date(evento.fecha).toLocaleString()}</p>
            </div>

            {/* Gestión de Miembros */}
            <div className="miembros-section">
                <h3>Miembros Participantes ({miembrosAsignados.length})</h3>
                <ul className="miembros-list">
                    {miembrosAsignados.length > 0 ? (
                        miembrosAsignados.map(miembro => (
                            // Asumimos que el miembro tiene una propiedad 'username'
                            <li key={miembro.id}>
                                {miembro.username} - {miembro.nombre} {miembro.apellido}
                            </li>
                        ))
                    ) : (
                        <li>Aún no hay miembros asignados.</li>
                    )}
                </ul>
                
                <div className="miembros-actions">
                    {isFuturo && (
                        <>
                            <button className="btn-asignar" onClick={handleAsignarMiembro}>
                                Asignar Miembro (Activo)
                            </button>
                            <button className="btn-quitar" onClick={handleQuitarMiembro}>
                                Quitar Miembro
                            </button>
                        </>
                    )}
                </div>
            </div>
            
            <div className="detalle-actions">
                <button className="btn-volver" onClick={() => navigate('/eventos')}>
                    Volver al Listado
                </button>
                
                <button className="btn-modificar" onClick={() => navigate(`/eventos/editar/${evento.id}`)}>
                    Modificar Nombre/Fecha {isFuturo ? '' : 'o Registrar Donaciones'}
                </button>

                {isFuturo && (
                    <button className="btn-eliminar-fisico" onClick={handleEliminar}>
                        Eliminar Físicamente
                    </button>
                )}
            </div>
        </div>
    );
};