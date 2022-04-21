package com.wojcikk.shopmanagementapi.invoices.purchase.service

import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.repository.PurchaseInvoiceRepo
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.wrap

class PurchaseInvoiceServiceImpl(
    private val purchaseInvoiceRepo: PurchaseInvoiceRepo
) : PurchaseInvoiceService {

    override fun getAll(): List<PurchaseInvoiceDTO>
    = wrap(isAuthenticated) {
        purchaseInvoiceRepo.findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): PurchaseInvoiceDTO {

    }

    override fun create(command: CreatePurchaseInvoice): PurchaseInvoiceDTO {
        TODO("Not yet implemented")
    }

    override fun createCorrection(command: CreatePurchaseInvoiceCorrection): PurchaseInvoiceDTO {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}