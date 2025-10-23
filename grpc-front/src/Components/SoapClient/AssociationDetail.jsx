import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getAssociations } from '../../Service/SoapClientService';
import { Association } from '../../Models/SoapClientAssociation';

const AssociationDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const orgId = location.state?.orgId;

  const [association, setAssociation] = useState(null);

  useEffect(() => {
    const fetchAssociation = async () => {
      if (!orgId) return;
      const data = await getAssociations([orgId]);
      if (data.length > 0) {
        setAssociation(new Association(data[0].id, data[0].name, data[0].address, data[0].phone));
      }
    };
    fetchAssociation();
  }, [orgId]);

  if (!orgId) return <p>No se proporcionó ID de organización.</p>;
  if (!association) return <p>Cargando asociación...</p>;

  return (
    <div style={{ padding: '1rem' }}>
      <h2>Información de la Asociación</h2>

      <button onClick={() => navigate(-1)} style={{ marginBottom: '1rem' }}>
        ← Volver
      </button>

      <ul>
        <li><strong>ID:</strong> {association.id}</li>
        <li><strong>Nombre:</strong> {association.name}</li>
        <li><strong>Dirección:</strong> {association.address}</li>
        <li><strong>Teléfono:</strong> {association.phone}</li>
      </ul>
    </div>
  );
};

export default AssociationDetail;
