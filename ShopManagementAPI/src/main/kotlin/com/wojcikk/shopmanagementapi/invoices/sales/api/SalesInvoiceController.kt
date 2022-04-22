package com.wojcikk.shopmanagementapi.invoices.sales.api

import com.wojcikk.shopmanagementapi.invoices.purchase.api.CreateInvoiceCorrectionRequest
import com.wojcikk.shopmanagementapi.invoices.sales.domain.SalesInvoice
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.sales.service.CreateSalesInvoice
import com.wojcikk.shopmanagementapi.invoices.sales.service.CreateSalesInvoiceCorrection
import com.wojcikk.shopmanagementapi.invoices.sales.service.SalesInvoiceService
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.web.bind.annotation.RestController

@RestController
class SalesInvoiceController(
    private val invoicesService: SalesInvoiceService,

    ) : SalesInvoicesAPI {

    override fun getAllSalesInvoices(): List<SalesInvoiceDTO>
    = isAuthenticated {
        return invoicesService.getAll()
    }

    override fun getSalesInvoice(invoiceId: Long): SalesInvoiceDTO
    = isAuthenticated {
        return invoicesService.get(invoiceId)
    }

    override fun createSalesInvoice(request: CreateSalesInvoiceRequest): SalesInvoiceDTO
    = wrap(SalesInvoice.canCreate)
    {
        invoicesService.create(CreateSalesInvoice(
            request.sellerId,
            request.businessEntityId,
            request.issuedAt,
            request.items
        ))
    }

    override fun createInvoiceCorrection(invoiceId: Long, requestBody: CreateInvoiceCorrectionRequest)
    = wrap(isAuthenticated) {
        invoicesService.createCorrection(CreateSalesInvoiceCorrection(
            invoiceId,
            requestBody.correctionIssueDate,
            requestBody.items
        ))

        Unit
    }

    override fun markInvoiceAsPayed(invoiceId: Long): SalesInvoiceDTO {
        return invoicesService.markAsPayed(invoiceId)
    }
}