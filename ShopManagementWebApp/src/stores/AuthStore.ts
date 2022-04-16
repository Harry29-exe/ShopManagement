import {AbstractStore} from "./AbstractStore";
import {ApiConfig} from "../apiclient/ApiConfig";
import {eraseCookie, setCookie} from "../utils/cookies";
import {AppMessage} from "./PopupStore";

const CsrfTokenLocalCookieName = "Csrf-Cookie-Cache"

export class AuthHolder {
    public readonly loggedIn: boolean;
    public readonly username: string | null;
    public readonly csrfToken: string | null;


    private constructor(loggedIn: boolean, username: string | null, csrfToken: string | null) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.csrfToken = csrfToken;
    }

    public static empty(): AuthHolder {
        return new AuthHolder(false, null, null)
    }

    public static new(username: string, csrfToken: string): AuthHolder {
        return new AuthHolder(true, username, csrfToken);
    }
}

export class AuthStore extends AbstractStore<AuthHolder> {

    constructor(authHolder: AuthHolder) {
        super(authHolder);
    }

    public login(requestBody: LoginRequest): Promise<AuthHolder | AppMessage> {
        return  fetch(ApiConfig.ApiAddress + "/login", {
            body: JSON.stringify(requestBody),
            method: 'POST',
            headers: {'Content-Type': "application/json"}
        }).then(response => {
            if (response.ok) {
                let authHolder = this.parseLoginResponse(requestBody.username, response)
                this.set(authHolder)
                return authHolder
            }
            if (response.status === 401 || response.status === 403) {
                return AppMessage.error("Bad credentials, please try again.");
            } else {
                return AppMessage.error("Something went wrong, please try again later.")
            }
        })

    }

    public logout() {
        eraseCookie(CsrfTokenLocalCookieName)
        this.set(AuthHolder.empty())
    }

    private parseLoginResponse(username: string, loginResponse: Response): AuthHolder {
        let csrfToken = loginResponse.headers.get(ApiConfig.CsrfHeaderName)
        if (csrfToken == null) {
            throw Error("csrf token header is null");
        }

        //todo change days to days fetched from server
        setCookie(CsrfTokenLocalCookieName, csrfToken, 5)

        let holder = AuthHolder.new(username, csrfToken);
        console.log("holder from parse login response", holder)
        return holder
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

export const authStore: AuthStore = new AuthStore(AuthHolder.empty())