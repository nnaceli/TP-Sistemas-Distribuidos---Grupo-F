// /c:/Users/sabri/OneDrive/Documentos/facultad/2do cuat/distribuidos/TP-Sistemas-Distribuidos---Grupo-F/grpc-front/src/Models/AdhesionEvento.js
export default class AdhesionEvento {
    constructor({
        eventoId = null,
        organizacionId = null,
        voluntarioId = null,
        nombre = '',
        apellido = '',
        telefono = '',
        email = ''
    } = {}) {
        this.eventoId = eventoId; // String
        this.organizacionId = organizacionId != null ? Number(organizacionId) : null; // Long -> Number
        this.voluntarioId = voluntarioId != null ? Number(voluntarioId) : null; // Long -> Number
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    static fromObject(obj = {}) {
        return new AdhesionEvento(obj);
    }

    static fromEventoYUsuario(evento, usuario) {
        return new AdhesionEvento({
            eventoId: evento.eventoId,
            organizacionId: evento.organizacionId,
            voluntarioId: usuario.id,
            nombre: usuario.nombre, 
            apellido: usuario.apellido,
            telefono: usuario.telefono,
            email: usuario.email
        });
    }

    toObject() {
        return {
            eventoId: this.eventoId,
            organizacionId: this.organizacionId,
            voluntarioId: this.voluntarioId,
            nombre: this.nombre,
            apellido: this.apellido,
            telefono: this.telefono,
            email: this.email
        };
    }

    toJSON() {
        return this.toObject();
    }

    // simple validation helper
    validate() {
        const errors = [];
        if (!this.eventoId) errors.push('eventoId is required');
        if (this.organizacionId != null && !Number.isInteger(this.organizacionId)) errors.push('organizacionId must be an integer');
        if (this.voluntarioId != null && !Number.isInteger(this.voluntarioId)) errors.push('voluntarioId must be an integer');
        if (this.email && !/^\S+@\S+\.\S+$/.test(this.email)) errors.push('email is invalid');
        return { valid: errors.length === 0, errors };
    }
}