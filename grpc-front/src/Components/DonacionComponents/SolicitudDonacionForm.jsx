// /c:/Users/sabri/OneDrive/Documentos/facultad/2do cuat/distribuidos/TP-Sistemas-Distribuidos---Grupo-F/grpc-front/src/Components/DonacionComponents/SolicitudDonacionForm/index.jsx
import React, { useState } from "react";
import { DonacionCategoria } from "../../Models/DonacionCategoria";
import { solicitarDonaciones } from "../../Service/DonacionService";


/*
    SolicitudDonacionForm
    - organizationId: number
    - solicitudId: number
    - donaciones: array of Donacion objects (at least one entry)
    - onSubmit: function({ organizationId, solicitudId, donaciones }) optional
*/

const emptyDonacion = () => ({
    id: null,
    categoria: "",
    descripcion: "",
    cantidad: 1
});

export default function SolicitudDonacionForm({ onSubmit }) {
    const [organizationId] = useState("101"); // fixed to 101
    const [solicitudId, setSolicitudId] = useState("");
    const [donaciones, setDonaciones] = useState([emptyDonacion()]);

    const handleDonacionChange = (index, field, value) => {
        setDonaciones((prev) =>
            prev.map((d, i) => (i === index ? { ...d, [field]: value } : d))
        );
    };

    const addDonacion = () => {
        setDonaciones((prev) => [...prev, emptyDonacion()]);
    };

    const removeDonacion = (index) => {
        setDonaciones((prev) => {
            if (prev.length <= 1) return prev; // keep at least one
            return prev.filter((_, i) => i !== index);
        });
    };
    
    const categorias = Object.values(DonacionCategoria);

    const handleSubmit = async (e) => {
        e.preventDefault();
         try{
             const payload = {
                 organizationId: Number(organizationId),
                 solicitudId: Number(solicitudId),
                 donaciones: donaciones.map((d) => ({
                     id: d.id,
                     categoria: d.categoria?.trim(),
                     cantidad: Number(d.cantidad),
                     descripcion: d.descripcion.trim(),
                 })),
             };


             const success= await solicitarDonaciones(payload);

            alert("Solicitud de donaciones enviada con éxito.");
        }catch(err){
            alert("Error al enviar la solicitud: " + err.message);
        }
    };

    

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>
                    Organization ID
                    <input
                        type="number"
                        value={organizationId}
                        readOnly
                        aria-readonly="true"
                    />
                </label>
            </div>

            <div>
                <label>
                    Solicitud ID
                    <input
                        type="number"
                        value={solicitudId}
                        onChange={(e) => setSolicitudId(e.target.value)}
                        required
                    />
                </label>
            </div>

            <fieldset>
                <legend>Donaciones</legend>
                {donaciones.map((d, idx) => (
                    <div key={idx} style={{ border: "1px solid #ddd", padding: 8, marginBottom: 8 }}>
                       <div>
                            <label>
                                Categoria
                                <select
                                    value={d.categoria}
                                    onChange={(e) => handleDonacionChange(idx, "categoria", e.target.value)}
                                    required
                                >
                                    <option value="">Seleccione una categoría</option>
                                    {categorias.map((categoria) => (
                                        <option key={categoria} value={categoria}>
                                            {categoria}
                                        </option>
                                    ))}
                                </select>
                            </label>
                        </div>

                        <div>
                            <label>
                                Cantidad
                                <input
                                    type="number"
                                    min="0"
                                    value={d.cantidad}
                                    onChange={(e) => handleDonacionChange(idx, "cantidad", e.target.value)}
                                    required
                                />
                            </label>
                        </div>

                        <div>
                            <label>
                                Descripción
                                <input
                                    type="text"
                                    value={d.descripcion}
                                    onChange={(e) => handleDonacionChange(idx, "descripcion", e.target.value)}
                                />
                            </label>
                        </div>

                        <div>
                            <button type="button" onClick={() => removeDonacion(idx)} disabled={donaciones.length <= 1}>
                                Remove
                            </button>
                        </div>
                    </div>
                ))}

                <div>
                    <button type="button" onClick={addDonacion}>
                        Add Donación
                    </button>
                </div>
            </fieldset>

            <div>
                <button type="submit">Submit Solicitud</button>
            </div>
        </form>
    );
}
