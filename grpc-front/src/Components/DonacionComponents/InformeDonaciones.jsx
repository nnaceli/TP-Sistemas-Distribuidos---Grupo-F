import React, { useState } from 'react';
import '../../CSS/Informe.css';

const InformeForm = () => {
  const [tipoDonacion, setTipoDonacion] = useState('recibidas');
  const [filtros, setFiltros] = useState({
    eliminado: {
      activo: true,
      valor: 'NO',
    },
    categoria: {
      activo: false,
      valor: 'ALIMENTOS',
    },
    rangoFechas: false,
  });
  const [fechas, setFechas] = useState({
    desde: '',
    hasta: '',
  });
  const [campos, setCampos] = useState({
    categoria: false,
    eliminado: false,
    cantidadTotal: false,
  });
  const [errorCampos, setErrorCampos] = useState('');

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

  const handleCampoChange = (e) => {
    const { name, checked } = e.target;
    setCampos(prevCampos => ({
      ...prevCampos,
      [name]: checked,
    }));
    if (checked) {
      setErrorCampos('');
    }
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    const algunCampoSeleccionado = Object.values(campos).some(valor => valor === true);
    if (!algunCampoSeleccionado) {
      setErrorCampos('Debes seleccionar al menos un campo para incluir en el informe.');
      return;
    }
    setErrorCampos('');

    const filtrosAplicados = {
      eliminado: filtros.eliminado.activo ? filtros.eliminado.valor : null,
      categoria: filtros.categoria.activo ? filtros.categoria.valor : null,
      rangoFechas: filtros.rangoFechas ? fechas : null,
    };
    
    const formData = {
      tipoDonacion,
      filtrosAplicados,
      camposInforme: campos,
    };
    
    console.log('Datos del informe a generar:', formData);
    alert('Formulario listo para enviar (revisa la consola para ver los datos)');
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Obtener Informe</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="tipoDonacion" className="form-label">Tipo de Informe</label>
          <select
            id="tipoDonacion"
            name="tipoDonacion"
            value={tipoDonacion}
            onChange={(e) => setTipoDonacion(e.target.value)}
            className="form-input"
          >
            <option value="recibidas">Donaciones Recibidas</option>
            <option value="solicitudes">Donaciones de Solicitudes</option>
          </select>
        </div>
        <fieldset className="form-fieldset">
          <legend className="form-legend">Aplicar Filtros (Opcional)</legend>
          <div className="checkbox-group">
            
            <div className="filtro-item">
              <label className="checkbox-label">
                <input
                  type="checkbox"
                  name="eliminado"
                  checked={filtros.eliminado.activo}
                  onChange={handleFiltroToggle}
                />
                Filtrar por "Eliminado"
              </label>
              {filtros.eliminado.activo && (
                <select
                  name="eliminado"
                  value={filtros.eliminado.valor}
                  onChange={handleFiltroValorChange}
                  className="filtro-dropdown"
                >
                  <option value="SI">SI</option>
                  <option value="NO">NO</option>
                </select>
              )}
            </div>
            
            <div className="filtro-item">
              <label className="checkbox-label">
                <input
                  type="checkbox"
                  name="categoria"
                  checked={filtros.categoria.activo}
                  onChange={handleFiltroToggle}
                />
                Filtrar por "Categoría"
              </label>
              {filtros.categoria.activo && (
                <select
                  name="categoria"
                  value={filtros.categoria.valor}
                  onChange={handleFiltroValorChange}
                  className="filtro-dropdown"
                >
                  <option value="ALIMENTOS">ALIMENTOS</option>
                  <option value="ROPA">ROPA</option>
                  <option value="JUGUETES">JUGUETES</option>
                </select>
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
        <fieldset className="form-fieldset">
          <legend className="form-legend">Campos a Incluir (Obligatorio)</legend>
          <div className="checkbox-group">
            <label className="checkbox-label">
              <input
                type="checkbox"
                name="categoria"
                checked={campos.categoria}
                onChange={handleCampoChange}
              />
              Categoría
            </label>
            <label className="checkbox-label">
              <input
                type="checkbox"
                name="eliminado"
                checked={campos.eliminado}
                onChange={handleCampoChange}
              />
              Eliminado
            </label>
            <label className="checkbox-label">
              <input
                type="checkbox"
                name="cantidadTotal"
                checked={campos.cantidadTotal}
                onChange={handleCampoChange}
              />
              Cantidad Total
            </label>
          </div>
          {errorCampos && <p className="error-message">{errorCampos}</p>}
        </fieldset>
        <button type="submit" className="submit-button">
          Generar Informe
        </button>
      </form>
    </div>
  );
};

export default InformeForm;