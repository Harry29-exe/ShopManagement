package com.wojcikk.shopmanagementapi.invoices.sales.service

import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSoldItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.util.*

interface SalesInvoiceService {

    fun getAll(): List<SalesInvoiceDTO>

    fun get(id: Long): SalesInvoiceDTO

    fun create(command: CreateSalesInvoice)

    fun createCorrection(command: CreateSalesInvoiceCorrection)

    fun markAsPayed(invoiceId: Long)

}

class CreateSalesInvoice(
    val sellerId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val products: List<NewSoldItemDTO>
) : Validated {

    //todo
    override fun isValid(): Boolean {
        return true
    }

}

class CreateSalesInvoiceCorrection(
    val invoiceId: Long,
    val correctionIssueDate: Date,
    val items: List<NewSoldItemDTO>
)