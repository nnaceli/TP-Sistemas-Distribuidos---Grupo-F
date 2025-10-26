export class President {
  constructor(id, name, address, phone, organization_id) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.organization_id = organization_id;
  }

  constructorVacio() {
    this.id = null;
    this.name = "";
    this.address = "";
    this.phone = "";
    this.organization_id = null;
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
    getOrganizationId() {
        return this.organization_id;
    }
    setOrganizationId(organization_id) {
        this.organization_id = organization_id;
    }
}