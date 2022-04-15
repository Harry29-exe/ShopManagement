package com.wojcikk.shopmanagementapi.invoices.api

import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.item.value.NewInvoiceItem
import org.springframework.web.bind.annotation.*
import java.util.Date

@RequestMapping("/sales-invoices/")
interface SalesInvoicesAPI {

    @GetMapping("all")
    fun getAllSalesInvoices(): List<SalesInvoiceDTO>

    @GetMapping("{invoiceId}")
    fun getSalesInvoice(@PathVariable invoiceId: Long): SalesInvoiceDTO

    @PostMapping
    fun createSalesInvoice(@RequestBody request: CreateSalesInvoiceRequest): SalesInvoiceDTO

    @PatchMapping("{invoiceId}/payed")
    fun markInvoiceAsPayed(@PathVariable invoiceId: Long): SalesInvoiceDTO

}

class CreateSalesInvoiceRequest(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewInvoiceItem>
)