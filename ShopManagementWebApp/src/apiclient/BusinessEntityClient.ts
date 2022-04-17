export class BusinessEntityClient {

}

export class BusinessEntityDTO {
    public readonly id: number;
    public readonly entityName: String;
    public readonly nip?: String;
    public readonly email?: String;
    public readonly phoneNumber?: String;


    constructor(id: number, entityName: String, nip?: String, email?: String, phoneNumber?: String) {
        this.id = id;
        this.entityName = entityName;
        this.nip = nip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}