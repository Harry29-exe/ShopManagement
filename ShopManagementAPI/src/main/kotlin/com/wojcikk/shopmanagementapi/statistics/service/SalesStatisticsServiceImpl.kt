package com.wojcikk.shopmanagementapi.statistics.service

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerStatisticsDTO
import com.wojcikk.shopmanagementapi.statistics.repository.SalesStatisticsRepository
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod
import org.springframework.stereotype.Service

@Service
class SalesStatisticsServiceImpl(
    private val salesStatisticsRepo: SalesStatisticsRepository
) : SalesStatisticsService {

    override fun getSellersResults(timePeriod: TimePeriod): List<SellerStatisticsDTO> {
        TODO("Not yet implemented")
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