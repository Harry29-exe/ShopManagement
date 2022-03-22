package com.wojcikk.shopmanagementapi.seller.dto

import java.math.BigDecimal
import java.util.Date

class SellersCmpReportDTO(
    val from: Date,
    val to: Date,
    val type: ReportTemporalType,


) {

    class UserSeries (val username: String, val values: List<Value>)

    class Value (val unitsSold: Int, val turnover: BigDecimal, val profit: BigDecimal)

}
