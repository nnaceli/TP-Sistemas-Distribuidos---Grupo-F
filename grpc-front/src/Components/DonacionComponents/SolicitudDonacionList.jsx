import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarSolicitudesDonaciones, eliminarSolicitud } from '../../Service/DonacionService';
import { DonacionCategoria } from '../../Models/DonacionCategoria';
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
                                        // onClick={() => handleTransferir(solicitud)}
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