import {ApiConfig} from "./ApiConfig";
import {BusinessEntityDTO} from "../dto/BusinessEntityDTO";
import {RequestResult} from "./RequestResult";
import {Api} from "./Api";
import {AppMessage} from "../stores/PopupStore";

const apiPrefix = ApiConfig.ApiAddress + "/business-entities"

export class BusinessEntityClient {

    public static async getAll(): Promise<RequestResult<BusinessEntityDTO[]>> {
        let result = await Api.fetchAuthorized(this.address("/all"), {});

        if (result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json())
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }

    public static async get(id: number): Promise<RequestResult<BusinessEntityDTO>> {
        let result = await Api.fetchAuthorized(this.address("/" + id), {});

        if (result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json())
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }

    public static async create(request: CreateBusinessEntityRequest): Promise<RequestResult<void>> {
        let result = await Api.fetchAuthorized(
            this.address("/new"), {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(request)
            });

        if (result.result) {
            if (result.result.ok) {
                return RequestResult.ok(undefined)
            } else {
                return RequestResult.error(AppMessage.httpError(result.result.status))
            }
        }

        return RequestResult.error(result.msg)
    }


    private static address(suffix: string): string {
        return apiPrefix + suffix
    }

}

class CreateBusinessEntityRequest {
    public name: String
    public nip: String
    public countryCode: String
    public city: String
    public email?: String
    public phoneNumber?: String
}

