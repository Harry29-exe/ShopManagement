import {AbstractStore} from "./AbstractStore";
import {ApiConfig} from "../apiclient/ApiConfig";
import {eraseCookie, setCookie} from "../utils/cookies";
import {AppMessage} from "./PopupStore";
import {LoginClient, LoginRequest} from "../apiclient/LoginClient";


export class AuthHolder {
    public readonly loggedIn: boolean;
    public readonly username: string | null;


    private constructor(loggedIn: boolean, username: string | null) {
        this.loggedIn = loggedIn;
        this.username = username;
    }

    public static empty(): AuthHolder {
        return new AuthHolder(false, null)
    }

    public static new(username: string): AuthHolder {
        return new AuthHolder(true, username);
    }
}

export class AuthStore extends AbstractStore<AuthHolder> {

    constructor(authHolder: AuthHolder) {
        super(authHolder);
    }

    public login(requestBody: LoginRequest): Promise<AuthHolder | AppMessage> {
        return LoginClient.login(requestBody)
            .then(response => {
                if (response.ok && response.result != null) {
                    let holder = AuthHolder.new(response.result.username)
                    this.set(holder)
                    return holder
                } else {
                    return response.msg
                }
            })

    }

    public logout() {
        LoginClient.logout()
            .then(response => {
                if (response.ok) {
                    this.set(AuthHolder.empty())
                } else {
                    throw Error()
                }
            })

    }



}

export const authStore: AuthStore = new AuthStore(AuthHolder.empty())