import { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { listarEventosExternos, inscribirUsuarioAEventoExterno } from "../../Service/EventoSolidarioService";

/**
 * EventosExternosList.jsx
 *
 * Shows a simple list/table of EventoExterno objects with fields:
 * - organizationId (long)
 * - eventoId (string)
 * - nombre (string)
 * - descripcion (string)
 * - fechaHora (datetime)
 *
 * Props:
 * - apiUrl: endpoint that returns an array of EventoExterno (default: /api/eventos-externos)
 * - initialEventos: optional initial array to avoid fetching
 */

const EventosExternosList = () => {
    const [eventos, setEventos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    
        // 1. Lógica de Carga de Datos
        useEffect(() => {
            const fetchEventos = async () => {
                try {
                    const data = await listarEventosExternos(); 
                    setEventos(data);
                    setLoading(false);
                } catch (err) {
                    setError(err.message || 'Error desconocido al cargar eventos.');
                    setLoading(false);
                }
            };
            fetchEventos();
        }, []);

            const isUserInscribed = (evento) => {
        const userSession = JSON.parse(localStorage.getItem('userSession'));
        const username = userSession?.username;
        return false; // Lógica para verificar si el usuario está inscripto en el evento
    };

    const handleInscribirse = async (evento, nombre) => {
            const userSession = JSON.parse(localStorage.getItem('userSession'));
            const username = userSession?.username;
    
            if (!username) {
                setError('Usuario no identificado');
                return;
            }
            if (window.confirm(`¿quiere inscribirse como voluntario a: "${nombre}"?`)) {
                try {
                    await inscribirUsuarioAEventoExterno(evento,username);
                              
                    alert(`Ha sido inscripto al evento`);
                } catch (err) {
                    console.error("Error al inscribirse al evento:", err);
                    setError(`Error al inscribirse: ${err.message}`);
                }
            }
        }

    const formatFechaHora = (value) => {
        if (!value) return "";
        const d = new Date(value);
        if (isNaN(d)) return String(value);
        return d.toLocaleString();
    };

    return (
        <div className="eventos-externos-list">
            <h2>Eventos Externos</h2>

            {error && <div className="error">Error: {error}</div>}
            {loading && <div className="loading">Cargando eventos...</div>}

            {!loading && !error && (
                <>
                    {eventos.length === 0 ? (
                        <div className="empty">No hay eventos externos para mostrar.</div>
                    ) : (
                        <table className="eventos-table" style={{ width: "100%", borderCollapse: "collapse" }}>
                            <thead>
                                <tr>
                                    <th style={{ textAlign: "left", borderBottom: "1px solid #ddd", padding: "8px" }}>Organization ID</th>
                                    <th style={{ textAlign: "left", borderBottom: "1px solid #ddd", padding: "8px" }}>Evento ID</th>
                                    <th style={{ textAlign: "left", borderBottom: "1px solid #ddd", padding: "8px" }}>Nombre</th>
                                    <th style={{ textAlign: "left", borderBottom: "1px solid #ddd", padding: "8px" }}>Descripción</th>
                                    <th style={{ textAlign: "left", borderBottom: "1px solid #ddd", padding: "8px" }}>Fecha y Hora</th>
                                </tr>
                            </thead>
                            <tbody>
                                {eventos.map((e) => (
                                    <tr key={e.eventoId || `${e.organizacionId}`} style={{ borderBottom: "1px solid #f0f0f0" }}>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>{e.organizacionId}</td>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>{e.eventoId}</td>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>{e.nombre}</td>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>{e.descripcion}</td>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>{formatFechaHora(e.fechaHora)}</td>
                                        <td style={{ padding: "8px", verticalAlign: "top" }}>
                                            <button className="btn-editar" onClick={() => handleInscribirse(e, e.nombre)}
                                               disabled={isUserInscribed(e)} >
                                        {isUserInscribed(e) ? 'Inscripto' : 'Inscribirse'}</button>
                                            
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </>
            )}
        </div>
    );
};

EventosExternosList.propTypes = {
    apiUrl: PropTypes.string,
    initialEventos: PropTypes.arrayOf(
        PropTypes.shape({
            organizacionId: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
            eventoId: PropTypes.string,
            nombre: PropTypes.string,
            descripcion: PropTypes.string,
            fechaHora: PropTypes.oneOfType([PropTypes.string, PropTypes.instanceOf(Date)]),
        })
    ),
};

export default EventosExternosList;