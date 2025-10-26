import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import {crearEvento, obtenerEventoPorId, actualizarEvento} from '../../Service/EventoSolidarioService';
import '../../CSS/EventoSolidarioForm.css';

export const EventoSolidarioForm = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const isEdit = id && id !== 'nueva';

    const [formData, setFormData] = useState({
        nombre: '',
        descripcion: '',
        fecha: '', // Formato yyyy-MM-ddTHH:mm
        miembros: [] // Simplificado
    });

    const [error, setError] = useState(null);
    const [isPastEvent, setIsPastEvent] = useState(false);


    useEffect(() => {
            if (isEdit) {
            const fetchEvento = async () => {
                try {
                    const data = await obtenerEventoPorId(id);
                    
                    // Formatea la fecha para el input datetime-local (simplificación)
                    const formattedDate = new Date(data.fecha).toISOString().slice(0, 16);
                    const isPast = new Date(data.fecha) < new Date();
                    
                    setFormData({
                        nombre: data.nombre,
                        descripcion: data.descripcion,
                        fecha: formattedDate,
                        miembros: data.miembros
                    });
                    setIsPastEvent(isPast);
                } catch (err) {
                    setError('Error al cargar el evento: ' + err.message);
                }
            };
            fetchEvento();
        }
    }, [id, isEdit]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);
        
        // La validación de la fecha a futuro se maneja mejor en el backend,
        // pero podemos hacer una verificación frontend aca:
        if (!isEdit && new Date(formData.fecha) < new Date()) {
             setError('La fecha y hora del evento debe ser a futuro.');
             return;
        }

        try {

            const dataToSend = { ...formData, miembros: [] };

            if (isEdit) {
                
                await actualizarEvento(id, dataToSend); 
                alert('Evento actualizado con éxito.');
            } else {
                await crearEvento(dataToSend);
                alert('Evento registrado con éxito.');
            }
            navigate('/eventos');
        } catch (err) {
            setError('Error en la operación: ' + err.message);
        }
    };

    return (
        <div className="evento-form-container">
            <h2>{isEdit ? 'Modificar Evento' : 'Crear Nuevo Evento'}</h2>
            {isPastEvent && isEdit && <p className="warning">⚠️ Evento Pasado: Solo se puede modificar el registro de donaciones repartidas.</p>}
            {error && <p className="error-message">{error}</p>}

            <form className='formulario-usuario' onSubmit={handleSubmit}>
                <label>
                    Nombre del Evento:
                    <input 
                        type="text" 
                        name="nombre" 
                        value={formData.nombre} 
                        onChange={handleChange} 
                        required 
                        disabled={isEdit}
                    />
                </label>
                
                <label>
                    Descripción:
                    <textarea 
                        name="descripcion" 
                        value={formData.descripcion} 
                        onChange={handleChange} 
                        required 
                    />
                </label>
                
                <label>
                    Fecha y Hora del Evento:
                    <input 
                        type="datetime-local" 
                        name="fecha" 
                        value={formData.fecha} 
                        onChange={handleChange} 
                        required 
                        min={!isEdit ? new Date().toISOString().slice(0, 16) : null}
                        disabled={isPastEvent && isEdit}
                    />
                </label>
                
                {/* Lógica simplificada de Miembros (la edición compleja va en Detalle) */}
                <p>Miembros Asignados: {formData.miembros.length} (Gestión en la vista de Detalle)</p>


                <div className="form-actions">
                    <button type="submit" className="btn-crear-usuario" disabled={isPastEvent && isEdit}>
                        {isEdit ? 'Guardar Cambios' : 'Registrar Evento'}
                    </button>
                    <button type="button" className="btn-cancelar" onClick={() => navigate('/eventos')}>
                        Cancelar
                    </button>
                </div>
            </form>
            {/* Aquí iría la sección para registrar donaciones repartidas si isPastEvent es true */}
            {isPastEvent && isEdit && (
                <div className="donaciones-repartidas-section">
                    <h3>Registro de Donaciones Repartidas (PENDIENTE DE IMPLEMENTACIÓN)</h3>
                    {/* Componente o formulario para manejar donaciones */}
                </div>
            )}
        </div>
    );
};