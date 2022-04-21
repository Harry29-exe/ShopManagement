package com.wojcikk.shopmanagementapi.invoices.purchase.service

import com.wojcikk.shopmanagementapi.exception.validation.ValidationException
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchaseInvoiceItemDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.utils.validation.Validated
import java.util.Date

interface PurchaseInvoiceService {

    fun getAll(): List<PurchaseInvoiceDTO>

    fun get(id: Long): PurchaseInvoiceDTO

    fun create(command: CreatePurchaseInvoice): PurchaseInvoiceDTO

    fun createCorrection(command: CreatePurchaseInvoiceCorrection): PurchaseInvoiceDTO

    fun delete(id: Long)

}

class CreatePurchaseInvoice(
    val purchaserId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewPurchaseInvoiceItemDTO>
) : Validated {

    override fun isValid(): Boolean {
        return purchaserId > 0 && businessEntityId > 0 &&
                items.isNotEmpty();
    }

}

class CreatePurchaseInvoiceCorrection(
    val correctionIssueDate: Date,
    val items: List<NewPurchaseInvoiceItemDTO>
)