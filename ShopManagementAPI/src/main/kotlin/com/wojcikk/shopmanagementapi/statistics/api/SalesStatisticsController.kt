package com.wojcikk.shopmanagementapi.statistics.api

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.service.SalesStatisticsService
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.web.bind.annotation.RestController

@RestController
class SalesStatisticsController(
    private val salesStatisticsService: SalesStatisticsService
) : SalesStatisticsAPI {

    override fun getSellersResults(request: TimePeriod): SellersResultsResponse {
        return SellersResultsResponse(
            request.from,
            request.to,
            salesStatisticsService.getSellersResults(request)
        )
    }

    override fun getSalesReports(request: TimePeriod): SalesReportDTO {
        return salesStatisticsService.getSalesReport(request)
    }

}