export class SellerSalesReportDTO {
    public sellerUserId: number
    public sellerFullname: String
    public transactions: number
    public salesTotal: number
}

export class SalesReportDTO {
    public from: Date
    public to: Date
    public data: SalesReportDataDTO
}

export class SalesReportDataDTO {
    public transactions: number
    public salesTotal: number
}