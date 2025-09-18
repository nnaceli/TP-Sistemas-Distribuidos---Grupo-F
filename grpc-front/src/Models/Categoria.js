export default class Categoria{
    constructor(id, donacionCategoria, descripcion, cantidad, eliminado){
        this.id = id;
        this.donacionCategoria = donacionCategoria;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.eliminado = eliminado;
    }
    constructorVacio(){
        this.id = null;
        this.donacionCategoria = null;
        this.descripcion = "";
        this.cantidad = 0;
        this.eliminado = false;
    }
    getId(){
        return this.id;
    }  
    getDonacionCategoria(){
        return this.donacionCategoria;
    }
    setDonacionCategoria(donacionCategoria){
        this.donacionCategoria = donacionCategoria;
    }
    getDescripcion(){
        return this.descripcion;
    }
    setDescripcion(descripcion){
        this.descripcion = descripcion;
    }
    getCantidad(){
        return this.cantidad;
    }
    setCantidad(cantidad){
        this.cantidad = cantidad;
    }
    getEliminado(){
        return this.eliminado;
    }
    setEliminado(eliminado){
        this.eliminado = eliminado;
    }
}