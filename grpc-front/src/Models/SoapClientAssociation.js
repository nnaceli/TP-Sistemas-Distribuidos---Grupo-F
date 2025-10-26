export class Association {
  constructor(id, name, address, phone) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
  }


constructorVacio(){
    this.id = null;
    this.name = "";
    this.address = "";
    this.phone = "";
}
    getId() {
        return this.id;
    }
    getName() {
        return this.name;
    }
    setName(name) {
        this.name = name;
    }
    getAddress() {
        return this.address;
    }
    setAddress(address) {
        this.address = address;
    }
    getPhone() {
        return this.phone;
    }
    setPhone(phone) {
        this.phone = phone;
    }
}