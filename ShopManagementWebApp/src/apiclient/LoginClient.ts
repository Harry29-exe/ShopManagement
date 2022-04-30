import {RequestResult} from "./config/RequestResult";
import {ApiConfig} from "./config/ApiConfig";
import {eraseCookie, setCookie} from "../utils/cookies";
import {AuthHolder} from "../stores/AuthStore";
import {AppMessage} from "../stores/PopupStore";
import {Api} from "./config/Api";

const BadCredentialsErrorMsg =  AppMessage.error("Bad credentials, please try again.")
const UndefinedLoginErrorMsg = AppMessage.error("Something went wrong, please try again later.")

export class LoginClient {

    public static login(request: LoginRequest): Promise<RequestResult<LoginResponse>> {
        return fetch(ApiConfig.ApiAddress + "/login", {
            body: JSON.stringify(request),
            method: 'POST',
            credentials: "include",
            headers: {'Content-Type': "application/json"}
        }).then(response => {
            if (response.ok) {
                let loginResponse = this.parseLoginResponse(request.username, response)

                return RequestResult.ok<LoginResponse>(loginResponse)
            } else if (response.status === 401 || response.status === 403) {
                return RequestResult.error(BadCredentialsErrorMsg)
            } else {
                return RequestResult.error(UndefinedLoginErrorMsg)
            }
        })
    }

    public static logout(): Promise<RequestResult<null>> {
        eraseCookie(ApiConfig.CsrfTokenLocalCookieName);
        Api.eraseCachedCsrfToken();
        //todo logout
        return Promise.resolve(RequestResult.ok(null))

    }

    private static parseLoginResponse(username: string, loginResponse: Response): LoginResponse {
        let csrfToken = loginResponse.headers.get(ApiConfig.CsrfHeaderName)
        if (csrfToken == null) {
            throw Error("csrf token header is null");
        }

        //todo change days to days fetched from server
        Api.updateCsrfToken(csrfToken);

        let response = new LoginResponse(username, csrfToken)
        return response
    }

}

export class LoginRequest {
    public readonly username: string;
    public readonly password: string;
    public readonly dontLogout: boolean


    constructor(username: string, password: string, dontLogout: boolean) {
        this.username = username;
        this.password = password;
        this.dontLogout = dontLogout;
    }
}

export class LoginResponse {

    public readonly username: string;
    public readonly csrfToken: string;


    constructor(username: string, csrfToken: string) {
        this.username = username;
        this.csrfToken = csrfToken;
    }
}