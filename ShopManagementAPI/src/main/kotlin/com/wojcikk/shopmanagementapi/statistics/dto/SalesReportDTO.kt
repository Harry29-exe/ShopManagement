package com.wojcikk.shopmanagementapi.statistics.dto

import java.util.Date

class SalesReportDTO(
    val from: Date,
    val to: Date,
    val data: SalesReportDataDTO
)