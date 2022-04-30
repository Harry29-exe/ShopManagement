import {ApiConfig} from "./config/ApiConfig";
import {RequestResult} from "./config/RequestResult";
import {Api} from "./config/Api";
import {AppMessage} from "../stores/PopupStore";
import {SalesReportDTO, SellerSalesReportDTO} from "../dto/SalesReportsDTOs";

const apiAddress = "/sales-reports"
export class SalesReportsClient {


    public static async getSellersResults(from: Date, to: Date): Promise<RequestResult<SellersResultsResponse>> {
        let result = await Api.fetchAuthorized(this.address("/sellers-results"), {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({from: from.getTime(), to: to.getTime()})
        })

        if (result.result) {
            if (!result.result.ok) {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }

            return RequestResult.ok(await result.result.json())
        }

        return RequestResult.error(AppMessage.error("Something went very wrong, please try again later"));
    }

    public static async getSalesReport(from: Date, to: Date): Promise<RequestResult<SalesReportDTO>> {
        let result = await Api.fetchAuthorized(this.address(""), {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({from: from, to: to})
        })

        if (result.result) {
            if (!result.result.ok) {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }

            return RequestResult.ok(await result.result.json())
        }

        return RequestResult.error(AppMessage.error("Something went very wrong, please try again later"));
    }

    private static address(suffix: string): string {
        return ApiConfig.ApiAddress + apiAddress + suffix
    }

}

export class SellersResultsResponse {
    public from: Date
    public to: Date
    public statistics: SellerSalesReportDTO[]
}