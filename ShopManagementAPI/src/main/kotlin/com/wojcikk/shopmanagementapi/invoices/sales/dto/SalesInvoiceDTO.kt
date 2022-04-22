package com.wojcikk.shopmanagementapi.invoices.sales.dto

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import java.util.Date

class SalesInvoiceDTO(
    val id: Long,
    val correctionId: Long?,
    val sellerFullname: String,
    val businessEntity: BusinessEntityDTO,
    val issueDate: Date,
    val isPayed: Boolean,
    val products: List<SoldItemDTO>,
)