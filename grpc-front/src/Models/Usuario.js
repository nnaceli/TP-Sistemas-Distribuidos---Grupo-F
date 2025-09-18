export default class Usuario {
  constructor(id, username, nombre, apellido, telefono, password, email, rol, activo) {
    this.id = id;
    this.username = username; 
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.password = password;
    this.email = email;
    this.rol = rol;
    this.activo = activo;
  }

  constructorVacio() {
    this.id = null;
    this.username = "";
    this.nombre = "";
    this.apellido = "";
    this.telefono = "";
    this.password = "";
    this.email = "";
    this.rol = "";
    this.activo = false;
  }

  getId() {
    return this.id;
  }
  getUsername() {
    return this.username;
  }
  setusername(username) {
    this.username = username;
  }

  // Métodos de la clase
  getNombre() {
    return this.nombre;
  }

  setNombre(nombre) {
    this.nombre = nombre;
  }
  getApellido() {
    return this.apellido;
  }
  setApellido(apellido) {
    this.apellido = apellido;
  }
  getTelefono() {
    return this.telefono;
  } 
  setTelefono(telefono) {
    this.telefono = telefono;
  }
  getPassword() {
    return this.password;
  }
  setPassword(password) {
    this.password = password;
  }
  getEmail() {
    return this.email;
  }

  setEmail(email) {
    this.email = email;
  }
  getRol() { 
    return this.rol;
  }
  setRol(rol) {
    this.rol = rol;
  }
  getActivo() {
    return this.activo;
  } 
}
