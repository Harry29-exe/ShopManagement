package com.wojcikk.shopmanagementapi.invoices.sales.dto

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import java.math.BigDecimal
import java.util.Date

class SalesInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val seller:
    val businessEntity: BusinessEntityDTO,
    val issueDate: Date,
    val isPayed: Boolean,
    val products: List<SalesInvoiceItemDTO>,
)

class SalesInvoiceItemDTO(
    val itemId: Long,
    val nameOnInvoice: String,
    val quantity: Long,
    val price: BigDecimal,
    val taxRate: BigDecimal,
    val discountPercentage: BigDecimal
)