package com.wojcikk.shopmanagementapi.invoices.dto

import com.wojcikk.shopmanagementapi.products.dto.ProductOnInvoiceDTO
import java.util.Date
import java.util.UUID

class SalesInvoiceDTO(
    val pubId: UUID,
    val businessEntity: BusinessEntityDTO,
    val issueDate: Date,
    val isPayed: Boolean,
    val products: List<ProductOnInvoiceDTO>,
)