import {getCookieValue, setCookie} from "../utils/cookies";
import {ApiConfig} from "./ApiConfig";
import {RequestResult} from "./RequestResult";
import {AppMessage} from "../stores/PopupStore";

interface CsrfHeader {
    "Csrf-Auth-Token": string
}

const NotAuthenticatedMsg = AppMessage.error("You must login to access this action");

export class Api {

    private static cachedCsrfToken: string | null = null

    public static csrfToken(): string | null {
        if (this.cachedCsrfToken) {
            return this.cachedCsrfToken
        } else {
            this.cachedCsrfToken = getCookieValue(ApiConfig.CsrfTokenLocalCookieName)
            return this.cachedCsrfToken
        }
    }

    public static async fetchAuthorized(address: string, request: RequestInit): Promise<RequestResult<Response>> {
        let csrfToken = this.csrfToken()
        if (!csrfToken) {
            return RequestResult.error(NotAuthenticatedMsg)
        }
        let csrfHeader = this.createCsrfHeader(csrfToken)
        request.headers = {...request.headers,  ...csrfHeader};
        request.credentials = "include";

        return RequestResult.ok(await fetch(address, request))
    }

    private static createCsrfHeader(header: string): CsrfHeader {
        return {"Csrf-Auth-Token": header}
    }

    public static eraseCachedCsrfToken() {
        this.cachedCsrfToken = null;
    }

    public static updateCsrfToken(token: string) {
        this.cachedCsrfToken = token;
        setCookie(ApiConfig.CsrfTokenLocalCookieName, token, 5)
    }

}