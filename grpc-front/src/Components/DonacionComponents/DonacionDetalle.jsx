import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { obtenerDonacionPorId, eliminarDonacion } from '../../Service/DonacionService';
import { DonacionCategoria } from '../../Models/DonacionCategoria';
import '../../CSS/DonacionDetalle.css';

export const DonacionDetalle = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [donacion, setDonacion] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchDonacion = async () => {
            try {
                const data = await obtenerDonacionPorId(id);
                setDonacion(data);
            } catch (err) {
                setError(err.message);
            }
        };
        fetchDonacion();
    }, [id]);

    const handleEliminar = async () => {
        if (window.confirm(`¿Estás seguro que quieres ELIMINAR lógicamente la donación ID ${id}?`)) {
            try {
                await eliminarDonacion(id);
                alert('Donación eliminada lógicamente con éxito.');
                navigate('/donaciones'); // Vuelve al listado
            } catch (err) {
                alert('Error al eliminar: ' + err.message);
            }
        }
    };

    const getNombreCategoria = (valor) => {
        const categoria = Object.keys(DonacionCategoria).find(key => DonacionCategoria[key] === valor);
        return categoria || 'Desconocida';
    };

    if (error) return <p className="error">{error}</p>;
    if (!donacion) return <p className="loading">Cargando detalle de donación...</p>;

    return (
        <div className="donacion-detalle-container">
            <h2>Detalle de Donación ID: {donacion.id}</h2>
            <div className="donacion-detalle-card">
                <p><strong>Categoría:</strong> {getNombreCategoria(donacion.categoria)}</p>
                <p><strong>Descripción:</strong> {donacion.descripcion}</p>
                <p><strong>Cantidad:</strong> {donacion.cantidad}</p>
                <p><strong>Estado:</strong> {donacion.eliminado ? '🔴 ELIMINADA (Baja Lógica)' : '🟢 Activa'}</p>
                
                {/* Campos de Auditoría */}
                <p className="auditoria-info">
                    <strong>Alta:</strong> {new Date(donacion.fechaAlta).toLocaleString()} por {donacion.usuarioAlta}
                </p>
                {donacion.fechaModificacion && (
                    <p className="auditoria-info">
                        <strong>Modificación:</strong> {new Date(donacion.fechaModificacion).toLocaleString()} por {donacion.usuarioModificacion}
                    </p>
                )}
            </div>
            
            <div className="detalle-actions">
                <button className="btn-volver" onClick={() => navigate('/donaciones')}>
                    Volver al Listado
                </button>
                
                <button className="btn-modificar" onClick={() => navigate(`/donaciones/editar/${donacion.id}`)}>
                    Modificar (Solo Descripción y Cantidad)
                </button>

                {!donacion.eliminado && (
                    <button className="btn-eliminar" onClick={handleEliminar}>
                        Eliminar Lógicamente
                    </button>
                )}
            </div>
        </div>
    );
};