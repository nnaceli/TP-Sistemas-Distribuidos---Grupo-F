export default class Donacion {
    constructor(
        id, 
        categoria, 
        descripcion, 
        cantidad, 
        eliminado, 
        fechaCreacion, 
        usernameCreacion, 
        fechaModificacion, 
        usernameModificacion
    ) {
        this.id = id;
        this.categoria = categoria; // Usará el valor numérico del ENUM (0, 1, 2, 3)
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.eliminado = eliminado;
        this.fechaCreacion = fechaCreacion; // Tipo Timestamp (Date object en JS)
        this.usernameCreacion = usernameCreacion;
        this.fechaModificacion = fechaModificacion;
        this.usernameModificacion = usernameModificacion;
    }

    /**
     * Constructor vacío para inicializar una Donación sin valores.
     */
    constructorVacio() {
        this.id = null;
        this.categoria = 0; // Default a ROPA (según el proto)
        this.descripcion = "";
        this.cantidad = 0;
        this.eliminado = false;
        this.fechaCreacion = null;
        this.usernameCreacion = "";
        this.fechaModificacion = null;
        this.usernameModificacion = "";
    }

    // ======================================
    // MÉTODOS GETTERS (para consistencia con Usuario.js)
    // ======================================

    getId() {
        return this.id;
    }

    getCategoria() {
        return this.categoria;
    }

    getDescripcion() {
        return this.descripcion;
    }

    getCantidad() {
        return this.cantidad;
    }

    getEliminado() {
        return this.eliminado;
    }

    getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    getUsernameCreacion() {
        return this.usernameCreacion;
    }
    
    getFechaModificacion() {
        return this.fechaModificacion;
    }
    
    getUsernameModificacion() {
        return this.usernameModificacion;
    }
}


export class DonacionDTO {
    constructor(categoria, descripcion, cantidad) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

}