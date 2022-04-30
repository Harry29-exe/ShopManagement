import {RequestResult} from "./config/RequestResult";
import {ApiConfig} from "./config/ApiConfig";
import {Api} from "./config/Api";
import {NewSoldItemDTO, SalesInvoiceDTO} from "../dto/SalesInvoiceDTOs";
import {AppMessage} from "../stores/PopupStore";

const csrfHeader = ApiConfig.CsrfHeaderName

export class SalesInvoiceClient {
    private static apiAddress = "/sales-invoices"

    public static async getAllSalesInvoices()
        : Promise<RequestResult<SalesInvoiceDTO[]>> {
        let response = await Api.fetchAuthorized(this.address("/all"), {})

        if (response.ok && response.result && response.result.ok) {
            let body = await response.result.json()
            return RequestResult.ok(body)
        } else {
            return RequestResult.error(response.msg)
        }
    }

    public static async getSalesInvoice(id: number)
        : Promise<RequestResult<SalesInvoiceDTO>> {
        let response = await Api.fetchAuthorized(
            this.address("/" + id), {}
        )

        if (response.result) {
            return RequestResult.ok(await response.result.json())
        } else {
            return RequestResult.error(response.msg)
        }
    }

    public static async createSalesInvoice(request: CreateSalesInvoiceRequest)
        : Promise<RequestResult<void>> {
        let response = await Api.fetchAuthorized(this.address(""), {
            method: 'POST',
            body: JSON.stringify(request),
            headers: {'Content-Type': 'application/json'}
        })
        if (response.result) {
            if (response.result.ok) {
                return RequestResult.ok(undefined)
            } else {
                return RequestResult.error(AppMessage.error("Could not create invoice, " +
                    "please verify data"))
            }
        }
        return RequestResult.error(response.msg)
    }

    private static address(suffix: string) {
        return ApiConfig.ApiAddress + this.apiAddress + suffix
    }

}

export class CreateSalesInvoiceRequest {
    public sellerId: number;
    public businessEntityId: number;
    public issuedAt: Date;
    public items: NewSoldItemDTO[];


    constructor(sellerId: number, businessEntityId: number, issuedAt: Date, items: NewSoldItemDTO[]) {
        this.sellerId = sellerId;
        this.businessEntityId = businessEntityId;
        this.issuedAt = issuedAt;
        this.items = items;
    }
}

class CreateSalesInvoiceCorrectionRequest {
    public correctionIssueDate: Date;
    public items: NewSoldItemDTO[];
}
