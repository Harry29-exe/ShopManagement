package com.wojcikk.shopmanagementapi.invoices.sales.api

import com.wojcikk.shopmanagementapi.invoices.purchase.api.CreateInvoiceCorrectionRequest
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchaseInvoiceItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSalesInvoiceItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import org.springframework.web.bind.annotation.*
import java.util.Date

@RequestMapping("/sales-invoices")
interface SalesInvoicesAPI {

    @GetMapping("all")
    fun getAllSalesInvoices(): List<SalesInvoiceDTO>

    @GetMapping("/{invoiceId}")
    fun getSalesInvoice(@PathVariable invoiceId: Long): SalesInvoiceDTO

    @PostMapping
    fun createSalesInvoice(@RequestBody request: CreateSalesInvoiceRequest): SalesInvoiceDTO

    @PostMapping("/{invoiceId}/correction")
    fun createInvoiceCorrection(
        @PathVariable invoiceId: Long,
        @RequestBody requestBody: CreateInvoiceCorrectionRequest
    )

    @PatchMapping("/{invoiceId}/payed")
    fun markInvoiceAsPayed(@PathVariable invoiceId: Long): SalesInvoiceDTO

}

class CreateSalesInvoiceRequest(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewSalesInvoiceItemDTO>
)

class CreateSalesInvoiceCorrectionRequest(
    val correctionIssueDate: Date,
    val items: List<NewPurchaseInvoiceItemDTO>
)

