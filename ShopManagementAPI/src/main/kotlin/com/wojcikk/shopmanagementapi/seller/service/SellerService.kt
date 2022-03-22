package com.wojcikk.shopmanagementapi.seller.service

import com.wojcikk.shopmanagementapi.seller.dto.ReportTemporalType
import com.wojcikk.shopmanagementapi.seller.dto.SellerReportDTO
import com.wojcikk.shopmanagementapi.seller.dto.SellersCmpReportDTO
import java.util.Date

interface SellerService {

    fun generateReport(command: GenerateCmpReport): SellerReportDTO

    fun generateCmpReport(command: GenerateCmpReport): SellersCmpReportDTO

}


class GenerateCmpReport(
    val username: String,
    val from: Date,
    val to: Date,
    val reportTemporalType: ReportTemporalType
)