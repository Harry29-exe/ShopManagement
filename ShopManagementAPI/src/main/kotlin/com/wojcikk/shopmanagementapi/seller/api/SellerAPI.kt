package com.wojcikk.shopmanagementapi.seller.api

import com.wojcikk.shopmanagementapi.seller.dto.SellerReportDTO
import com.wojcikk.shopmanagementapi.seller.dto.ReportTemporalType
import com.wojcikk.shopmanagementapi.seller.dto.SellersCmpReportDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.Date
import java.util.UUID

@RequestMapping("/seller")
interface SellerAPI {

    @PostMapping("/seller-report")
    fun generateReport(@RequestBody request: GenerateSellerReportRequest): SellerReportDTO

    @PostMapping("/sellers-cmp-report")
    fun generateCmpReport(@RequestBody request: GenerateSellersCmpReportRequest): SellersCmpReportDTO

}

class GenerateSellerReportRequest(
    val sellerId: UUID,
    val reportType: ReportTemporalType,
    val from: Date,
    val to: Date? = Date()
)

class GenerateSellersCmpReportRequest(
    val sellersIds: List<UUID>,
    val reportType: ReportTemporalType,
    val from: Date,
    val to: Date? = Date()
)