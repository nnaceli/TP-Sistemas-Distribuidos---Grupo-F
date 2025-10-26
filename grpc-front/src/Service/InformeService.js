const BASE_URL = 'http://127.0.0.1:5005/api/client/informes';

const getAuthHeaders = () => {
    const token = JSON.parse(localStorage.getItem('userSession'))?.token;
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };
};

export const generarInformeDonaciones = async (formData) => {
    try{
      const response = await fetch(`${BASE_URL}/donaciones`, {
        method: 'POST',
        headers: getAuthHeaders(),
        body: JSON.stringify(formData),
    });
    if (!response.ok) {
        throw new Error('Error al generar el informe de donaciones');
    }
    return response.json();
    } catch (error) {
        return { error: error.message };
    }
};
