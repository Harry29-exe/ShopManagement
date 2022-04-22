import {RequestResult} from "./RequestResult";
import {ApiConfig} from "./ApiConfig";
import {Api} from "./Api";
import {SalesInvoiceDTO} from "../dto/SalesInvoiceDTO";

const csrfHeader = ApiConfig.CsrfHeaderName

export class SalesInvoiceClient {
    private static apiAddress = "/sales-invoices"

    public static async getAllSalesInvoices(): Promise<RequestResult<SalesInvoiceDTO[]>> {
        let response = await Api.fetchAuthorized(this.address("/all"), {})

        if (response.ok && response.result && response.result.ok) {
            let body = await response.result.json()
            return RequestResult.ok(body)
        } else {
            return RequestResult.error(response.msg)
        }
    }

    public static async getSalesInvoice(id: number): Promise<RequestResult<SalesInvoiceDTO>> {
        let response = await Api.fetchAuthorized(
            this.address("/" + id), {}
        )

        if (response.result) {
            return RequestResult.ok(await response.result.json())
        } else {
            return RequestResult.error(response.msg)
        }
    }

    public static async createSalesInvoice(request: CreateSalesInvoiceRequest): Promise<RequestResult<SalesInvoiceDTO>> {
        let response = await Api.fetchAuthorized(this.apiAddress, {method: 'POST'})
        if (response.result) {
            return RequestResult.ok(await response.result.json())
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
    public items: NewSalesInvoiceItemDTO[]
}

export class NewSalesInvoiceItemDTO {
    public itemId: number;
    public quantity: number;
    public nameOnInvoice?: String;
    public price?: number;
    public taxRate?: number;
    public discount?: number;
}

