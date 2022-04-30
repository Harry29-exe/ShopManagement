package com.wojcikk.shopmanagementapi.statistics.dto

import java.math.BigDecimal

class SellerSalesReportDTO(
    val sellerUserId: Long,
    val sellerFullname: String,
    val transactions: Long,
    val salesTotal: BigDecimal
)