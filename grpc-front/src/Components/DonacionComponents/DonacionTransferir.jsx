import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { DonacionCategoria } from "../../Models/DonacionCategoria";
import { transferirDonaciones } from "../../Service/DonacionService";
import "./DonacionTransferir.css";

const emptyDonacion = () => ({
  categoria: "",
  descripcion: "",
  cantidad: 1,
});

export default function DonacionTransferir({ organizacionInicial = "101" }) {
  const navigate = useNavigate();
  const [organizacionId] = useState(organizacionInicial);
  const [solicitudId, setSolicitudId] = useState("SOL-");
  const [donaciones, setDonaciones] = useState([emptyDonacion()]);

  const categorias = Object.values(DonacionCategoria || {});

  const handleSolicitudIdChange = (e) => {
    const value = e.target.value;
    if (value === "SOL-") {
      setSolicitudId(value);
      return;
    }
    if (value.startsWith("SOL-")) {
      const numbers = value.substring(4);
      if (numbers === "" || /^\d+$/.test(numbers)) setSolicitudId(value);
    }
  };

  const handleDonacionChange = (index, field, value) => {
    setDonaciones((prev) => prev.map((d, i) => (i === index ? { ...d, [field]: value } : d)));
  };

  const addDonacion = () => setDonaciones((prev) => [...prev, emptyDonacion()]);
  const removeDonacion = (index) =>
    setDonaciones((prev) => (prev.length <= 1 ? prev : prev.filter((_, i) => i !== index)));

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      solicitudId: solicitudId,
      organizacionId: Number(organizacionId),
      donaciones: donaciones.map((d) => ({
        categoria: d.categoria?.trim(),
        descripcion: d.descripcion?.trim(),
        cantidad: Number(d.cantidad),
      })),
    };

    try {
      await transferirDonaciones(payload);
      navigate("/transferencias");
    } catch (err) {
      alert("Error al transferir: " + (err?.message || err));
    }
  };

  return (
    <form className="transferir-form" onSubmit={handleSubmit}>
      <div>
        <label>Organization ID</label>
        <input type="number" value={organizacionId} readOnly aria-readonly="true" />
      </div>

      <div>
        <label>Solicitud ID</label>
        <input
          type="text"
          value={solicitudId}
          onChange={handleSolicitudIdChange}
          pattern="SOL-\d*"
          placeholder="SOL-123"
          required
        />
      </div>

      <fieldset>
        <legend>Donaciones a transferir</legend>
        {donaciones.map((d, idx) => (
          <div key={idx} className="donacion-item">
            <div className="row">
              <div>
                <label>Categoria</label>
                <select
                  value={d.categoria}
                  onChange={(e) => handleDonacionChange(idx, "categoria", e.target.value)}
                  required
                >
                  <option value="">Seleccione</option>
                  {Object.values(DonacionCategoria || {}).map((cat) => (
                    <option key={cat} value={cat}>{cat}</option>
                  ))}
                </select>
              </div>

              <div>
                <label>Cantidad</label>
                <input
                  type="number"
                  min="1"
                  value={d.cantidad}
                  onChange={(e) => handleDonacionChange(idx, "cantidad", e.target.value)}
                  required
                />
              </div>
            </div>

            <div>
              <label>Descripción</label>
              <input
                type="text"
                value={d.descripcion}
                onChange={(e) => handleDonacionChange(idx, "descripcion", e.target.value)}
              />
            </div>

            <div>
              <button type="button" className="btn-remove" onClick={() => removeDonacion(idx)} disabled={donaciones.length <= 1}>
                Eliminar
              </button>
            </div>
          </div>
        ))}

        <div>
          <button type="button" className="btn-add" onClick={addDonacion}>Agregar donación</button>
        </div>
      </fieldset>

      <div>
        <button type="submit" className="btn-submit">Transferir Donaciones</button>
      </div>
    </form>
  ); 

}