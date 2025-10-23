import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const IdInput = () => {
  const [ids, setIds] = useState('');
  const navigate = useNavigate();

  const handleSubmit = () => {
    const parsed = ids
      .split(',')
      .map(id => parseInt(id.trim()))
      .filter(id => !isNaN(id));

    // Navegar a /presidents con los IDs como estado
    navigate('/soapclient/presidents/', { state: { ids: parsed } });
  };

  return (
    <div>
      <h2>Ingresá IDs de asociaciones (separados por coma)</h2>
      <input
        type="text"
        value={ids}
        onChange={e => setIds(e.target.value)}
        placeholder="Ej: 6, 5, 8"
      />
      <button onClick={handleSubmit}>Buscar presidentes</button>
    </div>
  );
};

export default IdInput;