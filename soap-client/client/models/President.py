class President:
    def __init__(self, id, name, address, phone, organization_id):
        self.id = id
        self.name = name
        self.address = address
        self.phone = phone
        self.organization_id = organization_id

    def __repr__(self):
        return (f"President(id={self.id}, name='{self.name}', address='{self.address}', "
                f"phone='{self.phone}', organization_id={self.organization_id})")

    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'address': self.address,
            'phone': self.phone,
            'organization_id': self.organization_id
        }