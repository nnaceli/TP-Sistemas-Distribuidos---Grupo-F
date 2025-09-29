import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarEventos } from '../../Service/EventoSolidarioService';
import '../../CSS/EventoSolidarioList.css';

export const EventoSolidarioList = () => {
    const [eventos, setEventos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchEventos = async () => {
            try {
                const data = await listarEventos();
                setEventos(data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };
        fetchEventos();
    }, []);

    const isFuturo = (fechaEvento) => {
        // Compara la fecha del evento con la fecha y hora actual
        return new Date(fechaEvento) > new Date();
    };
    
    // Función de ordenamiento para mostrar futuros primero, luego pasados, y por fecha.
    const eventosOrdenados = eventos.sort((a, b) => {
        const aFuturo = isFuturo(a.fecha);
        const bFuturo = isFuturo(b.fecha);
        
        if (aFuturo !== bFuturo) {
            return aFuturo ? -1 : 1; // Futuros primero
        }

        return new Date(a.fecha) - new Date(b.fecha); 
    });


    if (loading) return <p className="loading">Cargando listado de eventos...</p>;
    if (error) return <p className="error">Error: {error}</p>;

    return (
        <div className="evento-list-container">
            <h2>Listado de Eventos Solidarios</h2>
            
            <button 
                className="btn-crear" 
                onClick={() => navigate('/eventos/nuevo')}>
                + Crear Nuevo Evento (A Futuro)
            </button>

            <table className="evento-table">
                <thead>
                    <tr>
                        <th>Estado</th>
                        <th>Nombre</th>
                        <th>Fecha y Hora</th>
                        <th>Miembros</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {eventosOrdenados.map(evento => {
                        const esFuturo = isFuturo(evento.fecha);
                        return (
                            <tr key={evento.id} className={esFuturo ? 'evento-futuro' : 'evento-pasado'}>
                                <td>{esFuturo ? '✨ Futuro' : '✅ Pasado'}</td>
                                <td>{evento.nombre}</td>
                                <td>{new Date(evento.fecha).toLocaleString()}</td>
                                <td>{evento.miembros ? evento.miembros.length : 0}</td>
                                <td>
                                    <button onClick={() => navigate(`/eventos/${evento.id}`)}>
                                        Ver Detalle
                                    </button>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
};