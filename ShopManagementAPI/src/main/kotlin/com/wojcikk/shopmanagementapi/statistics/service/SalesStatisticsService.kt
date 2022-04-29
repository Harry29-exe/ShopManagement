package com.wojcikk.shopmanagementapi.statistics.service

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerStatisticsDTO
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod

interface SalesStatisticsService {

    fun getSellersResults(timePeriod: TimePeriod): List<SellerStatisticsDTO>

    fun getSalesReport(timePeriod: TimePeriod): SalesReportDTO

}