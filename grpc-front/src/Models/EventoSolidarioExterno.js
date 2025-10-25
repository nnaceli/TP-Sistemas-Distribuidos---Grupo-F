export default class EventoSolidarioExterno{
    constructor(id, nombre, descripcion, fecha){
        this.organizacionId = 101;
        this.eventoId = "EVT-"+ id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHora = fecha; 
    }
    constructorVacio(){
        this.organizacionId = 101;
        this.eventoId = null;
        this.nombre = "";
        this.descripcion = "";
        this.fechaHora = null;
    }
    getEventoId(){
        return this.eventoId;
    } 
    getNombre(){
        return this.nombre;
    }
    setNombre(nombre){
        this.nombre = nombre;
    }   
    getDescripcion(){
        return this.descripcion;
    }
    setDescripcion(descripcion){
        this.descripcion = descripcion;
    }     
    getFechaHora(){
        return this.fechaHora;
    }   
    setFechaHora(fecha){
        this.fechaHora = fecha;
    }
    
    getOrganizacionId(){
        return this.organizacionId;
    }
    setOrganizacionId(organizacionId){
        this.organizacionId = organizacionId;
    }
}