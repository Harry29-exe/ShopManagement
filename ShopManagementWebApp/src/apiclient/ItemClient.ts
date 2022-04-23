import {RequestResult} from "./RequestResult";
import {ItemDTO} from "../dto/ItemDTOs";
import {Api} from "./Api";
import {ApiConfig} from "./ApiConfig";
import {AppMessage} from "../stores/PopupStore";

const endpointAddress = ApiConfig.ApiAddress + "/items"

export class ItemClient {

    public static async getAll(): Promise<RequestResult<ItemDTO[]>> {
        let result = await Api.fetchAuthorized(this.address("/all"), {})
        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json());
            }

            return RequestResult.error(AppMessage.httpError(result.result.status));
        }

        return RequestResult.error(result.msg);
    }

    public static async get(id: number): Promise<RequestResult<ItemDTO>> {
        let result = await Api.fetchAuthorized(this.address(`/${id}`), {})
        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json());
            }

            return RequestResult.error(AppMessage.httpError(result.result.status));
        }

        return RequestResult.error(result.msg);
    }

    public static async create(request: CreateItemRequest): Promise<RequestResult<void>> {
        let result = await Api.fetchAuthorized(this.address(""), {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(request)
        });

        if (result.ok && result.result) {
            if (result.result.ok) {
                return RequestResult.ok(await result.result.json());
            }

            return RequestResult.error(AppMessage.httpError(result.result.status));
        }

        return RequestResult.error(result.msg);
    }

    private static address(postfix: string): string {
        return endpointAddress + postfix;
    }

}

export class CreateItemRequest {

}