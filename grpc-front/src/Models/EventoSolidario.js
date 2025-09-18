export default class EventoSolidario{
    constructor(id, nombre, descripcion, fecha, miembros){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.miembros = miembros; // Lista de usuarios
    }
    constructorVacio(){
        this.id = null;
        this.nombre = "";
        this.descripcion = "";
        this.fecha = null;
        this.miembros = []; // Lista de usuarios
    }
    getId(){
        return this.id;
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
    getMiembros(){
        return this.miembros;
    }     
    setMiembros(miembros){
        this.miembros = miembros;
    }
}