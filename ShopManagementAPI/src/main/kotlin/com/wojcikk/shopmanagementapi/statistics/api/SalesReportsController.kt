package com.wojcikk.shopmanagementapi.statistics.api

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.service.SalesReportsService
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.web.bind.annotation.RestController

@RestController
class SalesReportsController(
    private val salesReportsService: SalesReportsService
) : SalesReportsAPI {

    override fun getSellersResults(request: TimePeriod): SellersResultsResponse {
        return SellersResultsResponse(
            request.from,
            request.to,
            salesReportsService.getSellersResults(request)
        )
    }

    override fun getSalesReports(request: TimePeriod): SalesReportDTO {
        return salesReportsService.getSalesReport(request)
    }

}