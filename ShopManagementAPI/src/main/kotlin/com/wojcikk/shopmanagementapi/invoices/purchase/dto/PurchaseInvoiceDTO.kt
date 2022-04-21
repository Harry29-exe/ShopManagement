package com.wojcikk.shopmanagementapi.invoices.purchase.dto

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import java.util.Date

class PurchaseInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val purchaserUsername: String,
    val contractor: BusinessEntityDTO,
    val issueData: Date,
    val isPayed: Boolean,
    val products: List<ProductOnInvoiceDTO>
)