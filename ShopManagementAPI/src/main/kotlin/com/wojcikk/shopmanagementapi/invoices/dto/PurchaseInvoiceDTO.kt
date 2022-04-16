package com.wojcikk.shopmanagementapi.invoices.dto

import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import java.util.Date

class PurchaseInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val ordererUsername: String,
    val contractor: BusinessEntityDTO,
    val issueData: Date,
    val isPayed: Boolean,
    val products: List<ProductOnInvoiceDTO>
)