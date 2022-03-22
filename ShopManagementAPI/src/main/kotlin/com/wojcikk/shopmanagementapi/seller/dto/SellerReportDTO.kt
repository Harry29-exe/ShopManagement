package com.wojcikk.shopmanagementapi.seller.dto

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

class SellerReportDTO(
    val sellerId: UUID,
    val type: ReportTemporalType,
    val from: Date,
    val to: Date,
    val report: List<Value>


) {
    class Value(
        val units: Int,
        val turnover: BigDecimal,
        val profit: BigDecimal
    )
}

