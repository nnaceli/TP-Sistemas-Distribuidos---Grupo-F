import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarDonaciones, eliminarDonacion } from '../../Service/DonacionService';
import { DonacionCategoria } from '../../Models/DonacionCategoria';
import '../../CSS/DonacionList.css';

export const DonacionList = () => {
    const [donaciones, setDonaciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchDonaciones = async () => {
            try {
                const data = await listarDonaciones();
                // Filtra solo las donaciones NO eliminadas lógicamente para el listado principal
                setDonaciones(data.filter(d => !d.eliminado));
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

    const handleEliminar = async (id, descripcion) => {
        if (window.confirm(`¿Está seguro de dar de baja la donación: "${descripcion}"?`)) {
            try {
                await eliminarDonacion(id); 
                
                alert(`Donación ${id} dada de baja exitosamente.`);
                
                setDonaciones(prev => prev.filter(d => d.id !== id)); 
            } catch (err) {
                console.error("Error al eliminar la donación:", err);
                setError(`Error al dar de baja: ${err.message}`);
            }
        }
    };

    if (loading) return <p className="loading">Cargando inventario de donaciones...</p>;
    if (error) return <p className="error">Error: {error}</p>;

    return (
        <div className="donacion-list-container">
            <h2>Inventario de Donaciones</h2>
            
            <button 
                className="btn-crear" 
                onClick={() => navigate('/donaciones/nueva')}>
                + Registrar Nueva Donación
            </button>

            <button 
                className="btn-crear" 
                onClick={() => navigate('/informe-donaciones')}>
                Obtener Informe de Donaciones
            </button>

            <table className="donacion-table">
                <thead>
                    <tr>
                        <th>Categoría</th>
                        <th>Descripción</th>
                        <th>Cantidad</th>
                        <th>Eliminar</th>
                        <th>Modificar</th>
                    </tr>
                </thead>
                <tbody>
                    {donaciones.map(donacion => (
                        <tr key={donacion.id}>
                            <td>{getNombreCategoria(donacion.categoria)}</td>
                            <td>{donacion.descripcion.substring(0, 50)}...</td>
                            <td>{donacion.cantidad}</td>
                            <td>
                                <button className='btn-eliminar' onClick={() => handleEliminar(donacion.id, donacion.descripcion)}>
                                    Dar de baja
                                </button>
                            </td>
                            <td>
                                <button className='btn-editar' onClick={() => navigate(`/donaciones/editar/${donacion.id}`)}>
                                    Modificar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};