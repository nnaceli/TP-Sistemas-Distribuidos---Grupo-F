import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarSolicitudesDonaciones, eliminarSolicitud } from '../../Service/DonacionService';
import { DonacionCategoria } from '../../Models/DonacionCategoria';
import { listarSolicitudesDonaciones, eliminarSolicitud } from '../../Service/DonacionService';
import { listarSolicitudesDonaciones, eliminarSolicitud, transferirDonaciones } from '../../Service/DonacionService';
import '../../CSS/DonacionList.css';

export const SolicitudDonacionList = () => {
    const [solicitudes, setSolicitudes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchDonaciones = async () => {
            try {
                const data = await listarSolicitudesDonaciones();
                setSolicitudes(data);
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };
        fetchDonaciones();
    }, []);

    const getNombreCategoria = (valor) => {
        return DonacionCategoria[valor] || 'Desconocida';
    };

    const handleEliminar = async (solicitud ) => {
        if (window.confirm(`¿Está seguro de dar de baja la solicitud: "${solicitud.solicitudId}"?`)) {
            try {
                await eliminarSolicitud(solicitud); 
                
                alert(`Solicitud ${solicitud.solicitudId} dada de baja exitosamente.`);
                
                setSolicitudes(prev => prev.filter(d => d.solicitudId !== solicitud.solicitudId)); 
            } catch (err) {
                console.error("Error al eliminar la donación:", err);
                setError(`Error al dar de baja: ${err.message}`);
            }
        }
    };

    //TODO
    //handleTransferir
    const handleTransferir = async (solicitud) => {
        if (!window.confirm(`¿Confirmar transferencia de donaciones para la solicitud "${solicitud.solicitudId}" a la ONG ${solicitud.organizacionId}?`)) return;
        try {
            const payload = {
                solicitudId: solicitud.solicitudId,
                organizacionId: 101, // o reemplazar por el id correcto de la ONG donante
                donaciones: (solicitud.donaciones || []).map(d => ({
                    categoria: d.categoria,
                    descripcion: d.descripcion,
                    cantidad: Number(d.cantidad) || 0
                }))
            };
            await transferirDonaciones(payload);
            alert(`Transferencia para ${solicitud.solicitudId} notificada correctamente.`);
            setSolicitudes(prev => prev.filter(s => s.solicitudId !== solicitud.solicitudId));
        } catch (err) {
            console.error('Error al transferir:', err);
            setError(err.message || String(err));
            alert(`Error al transferir: ${err.message || err}`);
        }
    };

    if (loading) return <p className="loading">Cargando SOLICITUDES...</p>;
    if (error) return <p className="error">Error: {error}</p>;

    return (
        <div className="donacion-list-container">
            <h2>Solicitudes registradas</h2>
            
            <button 
                className="btn-crear" 
                onClick={() => navigate('/solicitud-donaciones/nueva')}>
                + Registrar Nueva Solicitud a nombre de la ONG
            </button>

            <table className="donacion-table" style={{ width: "100%", borderCollapse: "collapse" }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ONG ID</th>
                        <th>Donaciones</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {solicitudes.map((solicitud) => (
                        <tr key={solicitud.solicitudId}>
                            <td>{solicitud.solicitudId}</td>
                            <td>{solicitud.organizacionId}</td>
                            <td>
                                {solicitud.donaciones.map((d, index) => (
                                    <div key={index}>
                                        {d.categoria} - Descripción: {d.descripcion}
                                    </div>
                                ))}
                            </td>
                            <td>
                                {solicitud.organizacionId === 101 ? (
                                    <button
                                        className="btn-eliminar"
                                        onClick={() => handleEliminar(solicitud)}
                                    >
                                        Eliminar
                                    </button>
                                ) : (
                                    <button
                                        className="btn-editar"
                                        onClick={() => handleTransferir(solicitud)}
                                    >
                                        Transferir
                                    </button>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default SolicitudDonacionList;