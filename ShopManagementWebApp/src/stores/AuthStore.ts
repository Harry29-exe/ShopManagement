import {AbstractStore} from "./AbstractStore";
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

    public async login(requestBody: LoginRequest): Promise<AuthHolder | AppMessage> {
        let response = await LoginClient.login(requestBody)

        if (response.ok && response.result != null) {
            let holder = AuthHolder.new(response.result.username)
            console.log("setting holder")
            this.set(holder)
            return holder
        } else {
            return response.msg
        }
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