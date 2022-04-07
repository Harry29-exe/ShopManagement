package com.wojcikk.shopmanagementapi.invoices.api

import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.products.value.ProductWithQuantity
import org.springframework.web.bind.annotation.*
import java.util.Date
import java.util.UUID

@RequestMapping("/sales-invoices/")
interface SalesInvoicesAPI {

    @GetMapping("all")
    fun getAllSalesInvoices(): List<SalesInvoiceDTO>

    @GetMapping("{invoicePubId}")
    fun getSalesInvoice(@PathVariable invoicePubId: UUID): SalesInvoiceDTO

    @PostMapping
    fun createSalesInvoice(@RequestBody request: CreateSalesInvoiceRequest)

    @PatchMapping("{invoicePubId}/payed")
    fun markInvoiceAsPayed(@PathVariable invoicePubId: UUID): SalesInvoiceDTO

}

class CreateSalesInvoiceRequest(
    sellerPubId: UUID,
    businessEntityPubId: UUID,
    issuedAt: Date,
    products: List<ProductWithQuantity>
)