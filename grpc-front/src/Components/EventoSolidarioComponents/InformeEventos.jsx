import React, { useState } from 'react';
import '../../CSS/Informe.css';
import { generarInformeEventos } from '../../Service/InformeService.js';

const InformeForm = () => {
    const [isLoading, setIsLoading] = useState(false);
  const [apiResponse, setApiResponse] = useState(null);
  const [apiError, setApiError] = useState(null);
  const [filtros, setFiltros] = useState({
    usuario: {
      activo: true,
      valor: '',
    },
    rangoFechas: false,
  });
  const [fechas, setFechas] = useState({
    desde: '',
    hasta: '',
  });

  // El resultado será un listado agrupado por mes, mostrando en cada mes los datos del evento: día, 
    // nombre del evento, descripción y donaciones. 
//   const [campos, setCampos] = useState({
//     dia: false,
//     nombre: false,
//     descripcion: false,
//     donaciones: false,
//   });

  const handleFiltroToggle = (e) => {
    const { name, checked } = e.target;
    if (name === 'rangoFechas') {
      setFiltros(prev => ({ ...prev, rangoFechas: checked }));
    } else {
      setFiltros(prev => ({
        ...prev,
        [name]: {
          ...prev[name],
          activo: checked,
        },
      }));
    }
  };

  const handleFiltroValorChange = (e) => {
    const { name, value } = e.target;
    setFiltros(prev => ({
      ...prev,
      [name]: {
        ...prev[name],
        valor: value,
      },
    }));
  };

  
  const handleSubmit = async(e) => {
    e.preventDefault();

    setIsLoading(true);
    setApiResponse(null);
    setApiError(null);

    if ( !filtros.usuario.valor) {
        setApiError('El filtro de usuario es obligatorio');
        setIsLoading(false);
        return;
    }

    const filtrosAplicados = {
        usuario: filtros.usuario.activo ? filtros.usuario.valor : null,
        rangoFechas: filtros.rangoFechas ? fechas : null
    };
    
    const formData = {
      filtrosAplicados
    };
    
    const response = await generarInformeEventos(formData);
    if (response.error) {
        setApiError(response.error);
    } else {
        setApiResponse(response);
    }
    setIsLoading(false);
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Obtener Informe</h2>
      <form onSubmit={handleSubmit}>         
        <fieldset className="form-fieldset">
          <legend className="form-legend">Aplicar Filtros</legend>
          <div className="checkbox-group">
            <div>
                <label className='filtro-item' >
                <input
                  type="checkbox"
                  name="usuario"
                  checked={filtros.usuario.activo}
                />
                Filtrar por "Usuario"
                </label>
            
            {filtros.usuario.activo && (
              <div className="filtro-item">
                <label htmlFor="usuario" className="form-label">Usuario:</label>
                <input
                  type="text"
                  id="usuario"
                    name="usuario"
                    value={filtros.usuario.valor}
                    onChange={handleFiltroValorChange}
                  className="form-input"
                />
              </div>
            )}
            </div>
            
            <div className="filtro-item">
              <label className="checkbox-label">
                <input
                  type="checkbox"
                  name="rangoFechas"
                  checked={filtros.rangoFechas}
                  onChange={handleFiltroToggle}
                />
                Filtrar por "Calendario (entre fechas)"
              </label>
              {filtros.rangoFechas && (
                <div className="date-container">
                  <div className="date-input-group">
                    <label htmlFor="fechaDesde" className="form-label">Desde</label>
                    <input
                      type="date"
                      id="fechaDesde"
                      name="desde"
                      value={fechas.desde}
                      onChange={(e) => setFechas(prev => ({ ...prev, desde: e.target.value }))}
                      className="form-input"
                    />
                  </div>
                  <div className="date-input-group">
                    <label htmlFor="fechaHasta" className="form-label">Hasta</label>
                    <input
                      type="date"
                      id="fechaHasta"
                      name="hasta"
                      value={fechas.hasta}
                      onChange={(e) => setFechas(prev => ({ ...prev, hasta: e.target.value }))}
                      className="form-input"
                    />
                  </div>
                </div>
              )}
            </div>
          </div>
        </fieldset>
       
        <button type="submit" className="submit-button" disabled={isLoading}>
          {isLoading ? 'Generando Informe...' : 'Generar Informe'}
        </button>
      </form>

      <div className="response-area">
        {isLoading && (
          <div className="loading-message">
            Consultando la base de datos...
          </div>
        )}

        {apiError && (
          <div className="api-error-message">
            <strong>Error:</strong> {apiError}
          </div>
        )}
        {apiResponse && (
          <div className="api-response-container">
            <h3>Informe Generado Exitosamente</h3>
            <pre>
              <code>
                {JSON.stringify(apiResponse, null, 2)}
              </code>
            </pre>
          </div>
        )}
      </div>

    </div>
  );
};

export default InformeForm;