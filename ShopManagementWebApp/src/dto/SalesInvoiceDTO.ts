import {BusinessEntityDTO} from "../apiclient/BusinessEntityClient";

export class SalesInvoiceDTO {
    public readonly id: number;
    public readonly correctionId?: number;
    public readonly sellerFullname: String;
    public readonly businessEntity: BusinessEntityDTO;
    public readonly issueDate: Date;
    public readonly isPayed: Boolean;
    public readonly products: SalesInvoiceItemDTO[];
}

export class SalesInvoiceItemDTO {
    public readonly itemId: number;
    public readonly nameOnInvoice: String;
    public readonly quantity: number;
    public readonly price: number;
    public readonly taxRate: number;
    public readonly discountPercentage: number;
}