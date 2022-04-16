package com.wojcikk.shopmanagementapi.invoices.sales.service

import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSalesInvoiceItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.util.*

interface SalesInvoiceService {

    fun getAll(): List<SalesInvoiceDTO>

    fun get(id: Long): SalesInvoiceDTO

    fun create(command: CreateSalesInvoice): SalesInvoiceDTO

    fun markAsPayed(invoiceId: Long): SalesInvoiceDTO

}

class CreateSalesInvoice(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val products: List<NewSalesInvoiceItemDTO>
) : Validated {
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}