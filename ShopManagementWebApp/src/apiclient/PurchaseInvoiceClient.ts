import {RequestResult} from "./config/RequestResult";
import {NewPurchasedItemDTO, PurchaseInvoiceDTO} from "../dto/PurchaseInvoiceDTOs";
import {Api} from "./config/Api";
import {ApiConfig} from "./config/ApiConfig";
import {AppMessage} from "../stores/PopupStore";

const endpointAddress = ApiConfig.ApiAddress + "/purchase-invoices"

export class PurchaseInvoiceClient {

    public static async getAll(): Promise<RequestResult<PurchaseInvoiceDTO[]>> {
        let result = await Api.fetchAuthorized(this.address("/all"), {})
        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json())
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }

    public static async get(invoiceId: number): Promise<RequestResult<PurchaseInvoiceDTO>> {
        let result = await Api.fetchAuthorized(this.address(`/${invoiceId}`), {})

        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json())
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }

    public static async create(request: CreatePurchaseInvoiceRequest)
        : Promise<RequestResult<void>> {
        let result = await Api.fetchAuthorized(
            this.address(""), {
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(request),
                method: 'POST'
            })

        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(undefined, AppMessage.success("Invoice has been created"))
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }

    private static address(postfix: string): string {
        return endpointAddress + postfix;
    }

}

export class CreatePurchaseInvoiceRequest {
    public purchaserId: number;
    public businessEntityId: number;
    public issuedAt: Date;
    public items: NewPurchasedItemDTO[];


    constructor(purchaserId: number, businessEntityId: number, issuedAt: Date, items: NewPurchasedItemDTO[]) {
        this.purchaserId = purchaserId;
        this.businessEntityId = businessEntityId;
        this.issuedAt = issuedAt;
        this.items = items;
    }
}

class CreatePurchaseInvoiceCorrectionRequest {
    public correctionIssueDate: Date;
    public items: NewPurchasedItemDTO[];
}
