import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { crearDonacion, obtenerDonacionPorId, actualizarDonacion } from '../../Service/DonacionService';
import { DonacionCategoria } from '../../Models/DonacionCategoria';
import '../../CSS/DonacionForm.css';

export const DonacionForm = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const isEdit = id && id !== 'nueva';
    
    const [formData, setFormData] = useState({
        // CAMBIO 1: Usar el string como valor por defecto.
        categoria: 'ROPA',
        descripcion: '',
        cantidad: 0,
    });
    const [loading, setLoading] = useState(isEdit);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (isEdit) {
            const fetchDonacion = async () => {
                try {
                    const data = await obtenerDonacionPorId(id);
                    setFormData({
                        categoria: data.categoria,
                        descripcion: data.descripcion,
                        cantidad: data.cantidad
                    });
                    setLoading(false);
                } catch (err) {
                    setError('Error al cargar la donación: ' + err.message);
                    setLoading(false);
                }
            };
            fetchDonacion();
        }
    }, [id, isEdit]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: name === 'cantidad' ? Math.max(0, parseInt(value, 10)) : value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);

        if (!formData.descripcion.trim() || formData.cantidad <= 0) {
            setError('La descripción y la cantidad deben ser válidas.');
            return;
        }

        try {
            if (isEdit) {
                await actualizarDonacion(id, formData);
                alert('Donación actualizada con éxito.');
            } else {
                await crearDonacion(formData);
                alert('Donación registrada con éxito.');
            }
            navigate('/donaciones');
        } catch (err) {
            setError('Error en la operación: ' + err.message);
        }
    };

    if (loading) return <p className="loading">Cargando datos...</p>;

    return (
        <div className="donacion-form-container">
            <h2>{isEdit ? 'Modificar Donación' : 'Registrar Donación'}</h2>
            {error && <p className="error-message">{error}</p>}

            <form onSubmit={handleSubmit}>
                <label>
                    Categoría:
                     <select
                        name="categoria"
                        value={formData.categoria}
                        onChange={handleChange}
                        disabled={isEdit}
                    >
                        {/* CAMBIO 2: Mapear los valores (strings) del objeto DonacionCategoria */}
                        {Object.values(DonacionCategoria).map((categoriaValue) => (
                            <option
                                key={categoriaValue}
                                // El valor que se envía es el string de la categoría.
                                value={categoriaValue}
                            >
                                {/* El texto que ve el usuario también es el string. */}
                                {categoriaValue}
                            </option>
                        ))}
                    </select>
                </label>
                
                <label>
                    Descripción:
                    <input 
                        type="text" 
                        name="descripcion" 
                        value={formData.descripcion} 
                        onChange={handleChange} 
                        required 
                    />
                </label>
                
                <label>
                    Cantidad:
                    <input 
                        type="number" 
                        name="cantidad" 
                        value={formData.cantidad} 
                        onChange={handleChange} 
                        min="1" 
                        required 
                    />
                </label>

                <div className="form-actions">
                    <button type="submit" className="btn-guardar">
                        {isEdit ? 'Guardar Cambios' : 'Registrar'}
                    </button>
                    <button type="button" className="btn-cancelar" onClick={() => navigate('/donaciones')}>
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    );
};