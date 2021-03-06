package com.wojcikk.shopmanagementapi.invoices.sales.api

import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSoldItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.utils.FrontendCors
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/sales-invoices")
@FrontendCors
interface SalesInvoicesAPI {

    @GetMapping("all")
    fun getAllSalesInvoices(): List<SalesInvoiceDTO>

    @GetMapping("/{invoiceId}")
    fun getSalesInvoice(@PathVariable invoiceId: Long): SalesInvoiceDTO

    @PostMapping
    fun createSalesInvoice(@RequestBody request: CreateSalesInvoiceRequest)

    @PostMapping("/{invoiceId}/correction")
    fun createInvoiceCorrection(
        @PathVariable invoiceId: Long,
        @RequestBody requestBody: CreateSalesInvoiceCorrectionRequest
    )

    @PatchMapping("/{invoiceId}/payed")
    fun markInvoiceAsPayed(@PathVariable invoiceId: Long)

}

class CreateSalesInvoiceRequest(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewSoldItemDTO>
)

class CreateSalesInvoiceCorrectionRequest(
    val correctionIssueDate: Date,
    val items: List<NewSoldItemDTO>
)

