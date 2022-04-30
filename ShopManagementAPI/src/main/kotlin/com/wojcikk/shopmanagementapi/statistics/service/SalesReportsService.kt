package com.wojcikk.shopmanagementapi.statistics.service

import com.wojcikk.shopmanagementapi.statistics.dto.SalesReportDTO
import com.wojcikk.shopmanagementapi.statistics.dto.SellerSalesReportDTO
import com.wojcikk.shopmanagementapi.utils.dto.TimePeriod

interface SalesReportsService {

    fun getSellersResults(timePeriod: TimePeriod): List<SellerSalesReportDTO>

    fun getSalesReport(timePeriod: TimePeriod): SalesReportDTO

}