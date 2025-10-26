/*
{
  "query": "query { informeDonaciones(categoria: \"Ropa\", eliminado: \"no\") { categoria eliminado totalCantidad } }"
}
 {
  "query": "mutation { guardarFiltroDonaciones(filtro: { nombre: \"Ropa enero\", categoria: \"Ropa\", fechaInicio: \"2025-01-01\", fechaFin: \"2025-01-31\", eliminado: \"no\" }) { id nombre } }"
}

*/

export class InformeFiltroDonacionDTO {
    constructor(categoria, fechaInicio, fechaFin, eliminado) {
        this.categoria = categoria;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.eliminado = eliminado;
    }
}

export class InformeDonacionCamposDTO {
    constructor(categoria, eliminado, totalCantidad) {
        this.categoria = categoria;
        this.eliminado = eliminado;
        this.totalCantidad = totalCantidad;
    }
}

export class GuardarFiltroDTO {
    constructor(nombre, categoria, fechaInicio, fechaFin, eliminado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.eliminado = eliminado;
    }
}