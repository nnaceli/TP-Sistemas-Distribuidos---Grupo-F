import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getPresidents } from '../../Service/SoapClientService';

const PresidentList = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const ids = location.state?.ids || [];

  const [presidents, setPresidents] = useState([]);

  useEffect(() => {
    const fetchPresidents = async () => {
      if (ids.length === 0) return;
      const data = await getPresidents(ids);
      setPresidents(data);
    };
    fetchPresidents();
  }, [ids]);

  const handleViewAssociation = (orgId) => {
    navigate('/soapclient/association/', { state: { orgId } });
  };

  return (
    <div style={{ padding: '1rem' }}>
      <h2>Presidentes</h2>

      <button onClick={() => navigate(-1)} style={{ marginBottom: '1rem' }}>
        ← Volver
      </button>

      {presidents.length === 0 ? (
        <p>No se encontraron presidentes para los IDs ingresados.</p>
      ) : (
        <table border="1" cellPadding="8" style={{ width: '100%', marginBottom: '1rem' }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Teléfono</th>
              <th>Organización</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {presidents.map(p => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.name}</td>
                <td>{p.phone}</td>
                <td>{p.organization_id}</td>
                <td>
                  <button onClick={() => handleViewAssociation(p.organization_id)}>
                    Ver Asociación
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default PresidentList;
