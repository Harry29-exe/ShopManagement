package com.wojcikk.shopmanagementapi.statistics.service

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerSalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.repository.SalesReportsRepo
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.stereotype.Service

@Service
class SalesReportsServiceImpl(
    private val salesStatisticsRepo: SalesReportsRepo,
) : SalesReportsService {

    override fun getSellersResults(timePeriod: TimePeriod): List<SellerSalesReportDTO> {
        return salesStatisticsRepo.selectReportDataGroupBySeller(timePeriod)
    }

    override fun getSalesReport(timePeriod: TimePeriod): SalesReportDTO {
        val reportData = salesStatisticsRepo.selectSalesReportData(timePeriod)

        return SalesReportDTO(
            timePeriod.from,
            timePeriod.to,
            reportData
        )
    }
}