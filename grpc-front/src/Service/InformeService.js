import { InformeEventoDTO } from "../Models/InformeEventoDTO";

const BASE_URL = 'http://127.0.0.1:5005/';
const BASE_URL_EXCEL = 'http://127.0.0.1:5000/api/client/reportes_donaciones'

const dataForGraphQL = (formData) => {
    return {
        query: `
        query { informeDonaciones(
            categoria: "${formData.filtrosAplicados.categoria ? formData.filtrosAplicados.categoria : ''}", 
            eliminado: "${formData.filtrosAplicados.eliminado ? formData.filtrosAplicados.eliminado : ''}",
            fechaInicio: "${formData.filtrosAplicados.fechaInicio ? formData.filtrosAplicados.fechaInicio : ''}",
            fechaFin: "${formData.filtrosAplicados.fechaFin ? formData.filtrosAplicados.fechaFin : ''}"
        ) { 
            ${formData.camposInforme.categoria ? 'categoria' : ''}
            ${formData.camposInforme.eliminado ? 'eliminado' : ''}
            ${formData.camposInforme.totalCantidad ? 'totalCantidad' : ''}
        }
             }`
    };
}

const guardarFiltroDonacionesGraphQL = (formData) => {
    return {
        query: `
        mutation { guardarFiltroDonaciones(filtro: { 
            nombre: "${formData.nombre}", 
            categoria: "${formData.categoria}",
            fechaInicio: "${formData.fechaInicio}",
            fechaFin: "${formData.fechaFin}",
            eliminado: "${formData.eliminado}"
            }) { id nombre } }`
    };
}

export const generarInformeDonaciones = async (formData) => {
    //CHEQUEO DE ROLES
    const userSession = JSON.parse(localStorage.getItem('userSession'));
    const userRole = userSession.rol;
    if (!userRole) {
        return { error: 'Usuario no autenticado' };
    }
    else if (userRole !== 'PRESIDENTE' && userRole !== 'VOCAL') {
        return { error: 'No tienes permisos para generar este informe' };
    }
    try {
        const response = await fetch(`${BASE_URL}/graphql`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataForGraphQL(formData)),
        });
        if (!response.ok) {
            throw new Error('Error al generar el informe de donaciones');
        }
        return response.json();
    } catch (error) {
        return { error: error.message };
    }
};

export const generarInformeEventos = async (formData) => {
    //CHEQUEO DE ROLES
    const userSession = JSON.parse(localStorage.getItem('userSession'));
    const userFromAuth = userSession.username;
    const userRole = userSession.rol;
    if (!userFromAuth) {
        return { error: 'Usuario no autenticado' };
    } else if (userRole !== 'PRESIDENTE' && userFromAuth !== formData.filtrosAplicados.usuario) {
        return { error: 'No tienes permisos para generar este informe para otro usuario' };
    }

    const data = new InformeEventoDTO(
        formData.filtrosAplicados.usuario,
        formData.filtrosAplicados.rangoFechas ? formData.filtrosAplicados.rangoFechas.fechaDesde : null,
        formData.filtrosAplicados.rangoFechas ? formData.filtrosAplicados.rangoFechas.fechaDesde : null,
    );
    try {
        const response = await fetch(`${BASE_URL}/eventos-por-usuario`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        /*
        {
      "username": "juanprueba",
      "fechaInicio": "2025-10-16T00:00:00",
      "fechaFin": "2025-12-31T23:59:59"
    }
        */
        if (!response.ok) {
            throw new Error('Error al generar el informe de eventos');
        }
        return response.json();
    } catch (error) {
        return { error: error.message };
    }
};

export const guardarFiltroDonaciones = async (formData) => {
    try {
        const response = await fetch(`${BASE_URL}/graphql`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(guardarFiltroDonacionesGraphQL(formData)),
        });
        if (!response.ok) {
            throw new Error('Error al guardar el filtro de donaciones');
        }
        return response.json();
    }
    catch (error) {
        return { error: error.message };
    }
};


export const exportarEnExcelDonaciones = async () => {
    try {
        window.location.href = `${BASE_URL_EXCEL}/excel`; 
        console.log('Solicitud de descarga enviada. El navegador gestionará la descarga.');
    } catch (error) {
        console.error('Error al intentar iniciar la descarga:', error);
    }
};
