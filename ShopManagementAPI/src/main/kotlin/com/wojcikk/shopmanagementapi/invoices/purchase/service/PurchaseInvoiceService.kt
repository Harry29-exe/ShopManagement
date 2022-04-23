package com.wojcikk.shopmanagementapi.invoices.purchase.service

import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchasedItemDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.util.*

interface PurchaseInvoiceService {

    fun getAll(): List<PurchaseInvoiceDTO>

    fun get(id: Long): PurchaseInvoiceDTO

    fun create(command: CreatePurchaseInvoice)

    fun createCorrection(command: CreatePurchaseInvoiceCorrection)

    fun delete(id: Long)

}

class CreatePurchaseInvoice(
    val purchaserId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewPurchasedItemDTO>
) : Validated {

    override fun isValid(): Boolean {
        return purchaserId > 0 && businessEntityId > 0 &&
                items.isNotEmpty()
    }

}

class CreatePurchaseInvoiceCorrection(
    val invoiceId: Long,
    val correctionIssueDate: Date,
    val items: List<NewPurchasedItemDTO>
)