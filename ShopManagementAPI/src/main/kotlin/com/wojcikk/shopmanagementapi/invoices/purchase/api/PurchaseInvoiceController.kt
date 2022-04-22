package com.wojcikk.shopmanagementapi.invoices.purchase.api

import com.wojcikk.shopmanagementapi.invoices.purchase.domain.PurchaseInvoice
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.service.CreatePurchaseInvoice
import com.wojcikk.shopmanagementapi.invoices.purchase.service.CreatePurchaseInvoiceCorrection
import com.wojcikk.shopmanagementapi.invoices.purchase.service.PurchaseInvoiceService
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.web.bind.annotation.RestController

@RestController
class PurchaseInvoiceController(
    val invoiceService: PurchaseInvoiceService
) : PurchaseInvoicesAPI {

    override fun getAll(): List<PurchaseInvoiceDTO> = wrap(PurchaseInvoice.canRead) {
        invoiceService.getAll()
    }

    override fun get(id: Long): PurchaseInvoiceDTO = wrap(PurchaseInvoice.canRead)
    {
        invoiceService.get(id)
    }

    override fun create(request: CreatePurchaseInvoiceRequest): PurchaseInvoiceDTO = wrap(PurchaseInvoice.canCreate)
    {
        invoiceService.create(
            CreatePurchaseInvoice(
                request.purchaserId,
                request.businessEntityId,
                request.issuedAt,
                request.items
            )
        )
    }

    override fun createInvoiceCorrection(id: Long, request: CreateInvoiceCorrectionRequest) =
        wrap(PurchaseInvoice.canUpdate)
        {
            invoiceService.createCorrection(
                CreatePurchaseInvoiceCorrection(
                    id, request.correctionIssueDate, request.items
                )
            )

            Unit
        }

    override fun delete(id: Long) = wrap(PurchaseInvoice.canDelete)
    {
        invoiceService.delete(id)
    }
}