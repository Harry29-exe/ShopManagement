export class TableHeader<T> {
    public readonly columns: HeaderValue<T>[];
    public readonly onRowClick: (val: T) => any;

    private constructor(values: HeaderValue<T>[], onRowClick: (val: T) => any) {
        this.columns = values;
        this.onRowClick = onRowClick;
    }

    public static fromArray<T>(
        headerValues: [string, getValueFunc<T>][],
        onRowClick?: (val: T) => any
    ): TableHeader<T> {

        let values: HeaderValue<T>[] = [];
        for (let headerValue of headerValues) {
            values.push(HeaderValue.of(headerValue[0], headerValue[1]));
        }

        return new TableHeader(values, onRowClick? onRowClick: () => {});
    }


}

export type getValueFunc<T> = (obj: T) => any;

export class HeaderValue<T> {
    public readonly name: string;
    public readonly getValue: getValueFunc<T>;


    private constructor(hName: string, getValue: getValueFunc<T>) {
        this.name = hName;
        this.getValue = getValue;
    }

    public static of<T>(hName: string, getValue: getValueFunc<T>): HeaderValue<T> {
        return new HeaderValue(hName, getValue);
    }
}