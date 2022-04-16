package com.wojcikk.shopmanagementapi.invoices.purchase.api

import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import org.springframework.web.bind.annotation.RestController

@RestController
class PurchaseInvoiceController : PurchaseInvoicesAPI {

    override fun getAll(): List<PurchaseInvoiceDTO> {
        TODO("Not yet implemented")
    }

    override fun get(id: Long): PurchaseInvoiceDTO {
        TODO("Not yet implemented")
    }

    override fun create(request: CreatePurchaseInvoiceRequest): PurchaseInvoiceDTO {
        TODO("Not yet implemented")
    }

    override fun createInvoiceCorrection(id: Long, request: CreateInvoiceCorrectionRequest) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}