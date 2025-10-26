const BASE_URL = 'http://127.0.0.1:5005/api/client/informes';
const EXPORT_URL='';

const getAuthHeaders = () => {
    const token = JSON.parse(localStorage.getItem('userSession'))?.token;
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };
};

export const generarInformeDonaciones = async (formData) => {
    try{
    //   const response = await fetch(`${BASE_URL}/donaciones`, {
    //     method: 'POST',
    //     headers: getAuthHeaders(),
    //     body: JSON.stringify(formData),
    // });
    const response = '{"donaciones": [] }';
    if (!response.ok) {
        throw new Error('Error al generar el informe de donaciones');
    }
    return response.json();
    } catch (error) {
        return { error: error.message };
    }
};

export const generarInformeEventos = async (formData) => {
    try{
    //   const response = await fetch(`${BASE_URL}/eventos`, {
    //     method: 'POST',
    //     headers: getAuthHeaders(),
    //     body: JSON.stringify(formData),
    // });
    const response = '{"eventos": [] }';
    if (!response.ok) {
        throw new Error('Error al generar el informe de eventos');
    }
    return response.json();
    } catch (error) {
        return { error: error.message };
    }
};

//TODO: Implementar la exportación a Excel
export const exportarEnExcelDonaciones = async (informeData, tipoDonacion) => {
    try {
        console.log('IMPLEMENTAR: Exportando informe de donaciones a Excel:', informeData, tipoDonacion);
    } catch (error) {
        console.error('Error al exportar el informe de donaciones a Excel:', error);
    }
};
