import {AbstractStore} from "./AbstractStore";

export class AppMessage {
    public id: number = Math.random();
    public message: string;
    public msgType: MsgType;


    private constructor(message: string, msgType: MsgType) {
        this.message = message;
        this.msgType = msgType;
    }

    public static error(msg: string): AppMessage {
        return new AppMessage(msg, MsgType.ERROR)
    }

    public static warning(msg: string): AppMessage {
        return new AppMessage(msg, MsgType.WARNING)
    }

    public static success(msg: string): AppMessage {
        return new AppMessage(msg, MsgType.SUCCESS)
    }

    public static info(msg: string): AppMessage {
        return new AppMessage(msg, MsgType.INFO)
    }

    public static empty(): AppMessage {
        return new AppMessage("", MsgType.NONE)
    }

}

export enum MsgType {
    WARNING = "Warning",
    ERROR = "Error",
    SUCCESS = "Success",
    INFO = "Info",
    NONE = ""
}

export class PopupStore extends AbstractStore<AppMessage> {

    constructor(value: AppMessage) {
        super(value);
    }

    public setNew(msg: AppMessage) {
        this.set(msg)
    }

    public close() {
        this.set(AppMessage.empty())
    }

    public get msg(): AppMessage {
        return this.value
    }

}

export const popupStore: PopupStore = new PopupStore(AppMessage.empty())