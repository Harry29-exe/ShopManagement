package com.wojcikk.shopmanagementapi.invoices.purchase.dto

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import java.math.BigDecimal
import java.util.*

class PurchaseInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val purchaserFullname: String,
    val contractor: BusinessEntityDTO,
    val issueData: Date,
    val isPayed: Boolean,
    val products: List<PurchaseInvoiceItemDTO>
)

class PurchaseInvoiceItemDTO(
    val itemId: Long,
    val nameOnInvoice: String,
    val quantity: Long,
    val price: BigDecimal,
    val taxRate: BigDecimal,
    val discountPercentage: BigDecimal
)