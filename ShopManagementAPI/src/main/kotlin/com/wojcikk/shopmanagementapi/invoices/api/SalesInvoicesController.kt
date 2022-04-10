package com.wojcikk.shopmanagementapi.invoices.api

import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.perimission.SalesInvoicePermissions
import com.wojcikk.shopmanagementapi.invoices.service.CreateSalesInvoice
import com.wojcikk.shopmanagementapi.invoices.service.SalesInvoiceService
import com.wojcikk.shopmanagementapi.utils.secure.hasRole
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import org.springframework.web.bind.annotation.RestController

@RestController
class SalesInvoicesController(
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
    = hasRole(*SalesInvoicePermissions.CREATE) {
        return invoicesService.create(
            CreateSalesInvoice(
                request.sellerId,
                request.businessEntityId,
                request.issuedAt,
                request.products
            )

        )
    }

    override fun markInvoiceAsPayed(invoiceId: Long): SalesInvoiceDTO {
        return invoicesService.markAsPayed(invoiceId)
    }
}