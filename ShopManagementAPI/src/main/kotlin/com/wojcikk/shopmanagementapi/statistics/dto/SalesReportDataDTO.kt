package com.wojcikk.shopmanagementapi.statistics.dto

import java.math.BigDecimal

class SalesReportDataDTO(
    val transactions: Long,
    val salesTotal: BigDecimal
) {
}