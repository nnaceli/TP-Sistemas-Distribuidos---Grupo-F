class Association:
    def __init__(self, id, name, address, phone):
        self.id = id
        self.name = name
        self.address = address
        self.phone = phone

    def __repr__(self):
        return f"Association(id={self.id}, name='{self.name}', address='{self.address}', phone='{self.phone}')"

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'address': self.address,
            'phone': self.phone
        }