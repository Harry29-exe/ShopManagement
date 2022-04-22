import {BusinessEntityDTO} from "../apiclient/BusinessEntityClient";

export class SalesInvoiceDTOs {
    public readonly id: number;
    public readonly correctionId?: number;
    public readonly sellerFullname: String;
    public readonly businessEntity: BusinessEntityDTO;
    public readonly issueDate: Date;
    public readonly isPayed: Boolean;
    public readonly products: SoldItemDTO[];
}

export class SoldItemDTO {
    public readonly itemId: number;
    public readonly nameOnInvoice: String;
    public readonly quantity: number;
    public readonly price: number;
    public readonly taxRate: number;
    public readonly discountPercentage: number;
}

export class NewSoldItemDTO {
    public readonly itemId: number;
    public readonly quantity: number;
    public readonly nameOnInvoice?: String;
    public readonly price?: number;
    public readonly taxRate?: number;
    public readonly discount?: number;
}

