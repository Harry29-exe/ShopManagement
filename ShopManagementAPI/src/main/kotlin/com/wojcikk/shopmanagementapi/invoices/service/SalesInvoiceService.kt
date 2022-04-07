package com.wojcikk.shopmanagementapi.invoices.service

import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.products.value.ProductWithQuantity
import java.util.Date
import java.util.UUID

interface SalesInvoiceService {

    fun getAll(): List<SalesInvoiceDTO>

    fun getByPubId(pubId: UUID): SalesInvoiceDTO

    fun create(command: CreateSalesInvoice)

    fun markAsPayed(invoicePubId: UUID): SalesInvoiceDTO

}

class CreateSalesInvoice(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val products: List<ProductWithQuantity>
)