import {AbstractStore} from "./AbstractStore";
import {ApiConfig} from "../apiclient/ApiConfig";
import {setCookie} from "../utils/cookies";

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

    constructor() {
        super(AuthHolder.empty());
    }

    public login(requestBody: LoginRequest): Promise<AuthHolder> {
        return  fetch(ApiConfig.ApiAddress, {
            body: JSON.stringify(requestBody)
        }).then(response => {
            if (response.ok) {
                let authHolder = this.parseLoginResponse(requestBody.username, response)
                this.set(authHolder)
                return authHolder
            }
            throw Error()
        })

    }

    public get username(): string {
        if (!this.value.username) {
            return "_"
        }
        return this.value.username;
    }

    public get csrfToken(): string {
        if(!this.value.csrfToken) {
            throw new Error();
        }

        return this.value.csrfToken;
    }

    private parseLoginResponse(username: string, loginResponse: Response): AuthHolder {
        let csrfToken = loginResponse.headers.get(ApiConfig.CsrfHeaderName)
        if (csrfToken == null) {
            throw Error("csrf token header is null");
        }

        //todo change days to days fetched from server
        setCookie(CsrfTokenLocalCookieName, csrfToken, 5)

        return AuthHolder.new(username, csrfToken)
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

export const authStore: AuthStore = new AuthStore()