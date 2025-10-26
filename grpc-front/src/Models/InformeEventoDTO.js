/* de forma 
{
  "username": "juanprueba",
  "fechaInicio": "2025-10-16T00:00:00",
  "fechaFin": "2025-12-31T23:59:59"
}
*/

export class InformeEventoDTO {
    constructor(username, fechaInicio, fechaFin) {
        this.username = username;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}