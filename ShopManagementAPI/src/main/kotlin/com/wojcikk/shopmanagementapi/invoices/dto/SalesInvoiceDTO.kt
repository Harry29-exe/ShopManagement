package com.wojcikk.shopmanagementapi.invoices.dto

import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import java.util.Date

class SalesInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val businessEntity: BusinessEntityDTO,
    val issueDate: Date,
    val isPayed: Boolean,
    val products: List<ProductOnInvoiceDTO>,
)