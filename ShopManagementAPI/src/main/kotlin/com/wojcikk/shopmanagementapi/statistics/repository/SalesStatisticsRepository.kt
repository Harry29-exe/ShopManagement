package com.wojcikk.shopmanagementapi.statistics.repository

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDataDTO
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod

interface SalesStatisticsRepository {

    fun selectSalesReportData(timePeriod: TimePeriod): SalesReportDataDTO

    fun selectSalesReportData(timePeriod: TimePeriod, sellerId: Long): SalesReportDataDTO

}