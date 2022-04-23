export class ItemDTO {
    public id: number;
    public name: String;
    public codeName: String;
    public description: String;
    public currentPrice: number;
    public priceHistory: ItemPriceDTO[];
}

export class ItemPriceDTO {
    public setDate: Date;
    public price: number;
}