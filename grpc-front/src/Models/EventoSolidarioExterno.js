export default class EventoSolidarioExterno{
    constructor(id, nombre, descripcion, fecha){
        this.organizacionId = 101;
        this.eventoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha; 
    }
    constructorVacio(){
        this.organizacionId = 101;
        this.eventoId = null;
        this.nombre = "";
        this.descripcion = "";
        this.fecha = null;
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
    getFecha(){
        return this.fecha;
    }   
    setFecha(fecha){
        this.fecha = fecha;
    }
    
    getOrganizacionId(){
        return this.organizacionId;
    }
    setOrganizacionId(organizacionId){
        this.organizacionId = organizacionId;
    }
}