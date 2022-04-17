import {AppMessage} from "../stores/PopupStore";

export class RequestResult<T> {

    public readonly ok: boolean;
    public readonly msg: AppMessage;
    public readonly result: T | null;


    private constructor(ok: boolean, result: T | null, msg: AppMessage) {
        this.ok = ok;
        this.msg = msg;
        this.result = result;
    }

    public static ok<T>(result: T, msg?: AppMessage): RequestResult<T> {
        return new RequestResult<T>(true, result, msg? msg: AppMessage.empty())
    }

    public static error(msg: AppMessage): RequestResult<any> {
        return new RequestResult(false, null, msg)
    }

}