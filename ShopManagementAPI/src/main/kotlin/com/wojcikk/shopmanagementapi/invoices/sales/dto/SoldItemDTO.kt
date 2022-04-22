package com.wojcikk.shopmanagementapi.invoices.sales.dto

import java.math.BigDecimal

class SoldItemDTO(
    val itemId: Long,
    val nameOnInvoice: String,
    val quantity: Long,
    val price: BigDecimal,
    val taxRate: BigDecimal,
    val discountPercentage: BigDecimal
)