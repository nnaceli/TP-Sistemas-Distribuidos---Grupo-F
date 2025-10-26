import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarEventos, eliminarEvento, exponerEvento, inscribirUsuario } from '../../Service/EventoSolidarioService';
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

    const handleExponer = async (id,nombre) => {
        if (window.confirm(`¿Está seguro de exponer el evento a otras organizaciones: "${nombre}"?`)) {
            try {
                await exponerEvento(id); 
                
                alert(`Evento "${nombre}" (ID: ${id}) expuesto a otras organizaciones.`);
            } catch (err) {
                console.error("Error al exponer el evento:", err);
                setError(`Error al exponer evento: ${err.message}`);
            }
        }
    }

    const handleInscribirse = async (id, nombre) => {
        const userSession = JSON.parse(localStorage.getItem('userSession'));
        const username = userSession?.username;

        if (!username) {
            setError('Usuario no identificado');
            return;
        }
        if (window.confirm(`¿quiere inscribirse como voluntario a: "${nombre}"?`)) {
            try {
                await inscribirUsuario(id,username); 
                               setEventos(prev => prev.map(evento => {
                    if (evento.id === id) {
                        return {
                            ...evento,
                            miembros: [...(evento.miembros || []), username]
                        };
                    }
                    return evento;
                }));
                
                alert(`Ha sido inscripto al evento`);
            } catch (err) {
                console.error("Error al inscribirse al evento:", err);
                setError(`Error al inscribirse: ${err.message}`);
            }
        }
    }

    const isUserInscribed = (evento) => {
        const userSession = JSON.parse(localStorage.getItem('userSession'));
        const username = userSession?.username;
        return evento.miembros?.includes(username);
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
                onClick={() => navigate('/eventos/nuevo')}>
                + Registrar Nuevo Evento
            </button>

                        <button 
                className="btn-crear" 
                onClick={() => navigate('/informe-eventos')}>
                Obtener Informe de Eventos
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
                                        onClick={() => navigate(`/eventos/editar/${evento.id}`)}>
                                        Modificar
                                    </button>
                                    <button 
                                        className="btn-eliminar"
                                        onClick={() => handleEliminar(evento.id, evento.nombre)}>
                                        Eliminar
                                    </button>
                                    <button
                                        className={`btn-inscribirse ${isUserInscribed(evento) ? 'inscripto' : ''}`}
                                        onClick={() => handleInscribirse(evento.id, evento.nombre)}
                                        disabled={isUserInscribed(evento)}
                                    >
                                        {isUserInscribed(evento) ? 'Inscripto' : 'Inscribirse'}
                                    </button>
                                    <button
                                        onClick={()=> handleExponer(evento.id, evento.nombre)}>
                                        Exponer
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