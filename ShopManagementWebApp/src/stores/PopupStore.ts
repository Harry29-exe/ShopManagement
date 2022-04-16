import {AbstractStore} from "./AbstractStore";

export class PopupMsg{
    public id: number = Math.random();
    public message: string;
    public msgType: MsgType;


    private constructor(message: string, msgType: MsgType) {
        this.message = message;
        this.msgType = msgType;
    }

    public static error(msg: string): PopupMsg {
        return new PopupMsg(msg, MsgType.ERROR)
    }

    public static warning(msg: string): PopupMsg {
        return new PopupMsg(msg, MsgType.WARNING)
    }

    public static success(msg: string): PopupMsg {
        return new PopupMsg(msg, MsgType.SUCCESS)
    }

    public static info(msg: string): PopupMsg {
        return new PopupMsg(msg, MsgType.INFO)
    }

    public static empty(): PopupMsg {
        return new PopupMsg("", MsgType.NONE)
    }

}

export enum MsgType {
    WARNING = "Warning",
    ERROR = "Error",
    SUCCESS = "Success",
    INFO = "Info",
    NONE = ""
}

export class PopupStore extends AbstractStore<PopupMsg> {

    constructor(value: PopupMsg) {
        super(value);
    }

    public setNew(msg: PopupMsg) {
        this.set(msg)
    }

    public close() {
        this.set(PopupMsg.empty())
    }

    public get msg(): PopupMsg {
        return this.value
    }

}

export const popupStore: PopupStore = new PopupStore(PopupMsg.empty())