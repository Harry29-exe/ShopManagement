package com.wojcikk.shopmanagementapi.statistics.api

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerSalesReportDTO
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.Date

@RequestMapping("/sales-statistics")
interface SalesStatisticsAPI {

    @PostMapping("/sellers")
    fun getSellersResults(@RequestBody request: TimePeriod): SellersResultsResponse

    @PostMapping("/report")
    fun getSalesReports(@RequestBody request: TimePeriod): SalesReportDTO

}

class SellersResultsResponse(
    val from: Date,
    val to: Date,
    val statistics: List<SellerSalesReportDTO>
)