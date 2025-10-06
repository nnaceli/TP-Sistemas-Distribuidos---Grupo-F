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
        categoria: DonacionCategoria.ROPA, // Valor por defecto
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
        
        // Validación básica
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

    const categorias = Object.keys(DonacionCategoria).filter(key => isNaN(Number(key)));

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
                        disabled={isEdit} // No se debe modificar la categoría en la edición
                    >
                        {categorias.map(cat => (
                            <option key={cat} value={cat}>
                                {cat}
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