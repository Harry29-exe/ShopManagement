package com.wojcikk.shopmanagementapi.item.dto

import java.math.BigDecimal

class ProductOnInvoiceDTO(
    val codeName: String,
    val priceOnInvoice: BigDecimal,
    val taxRate: BigDecimal,
    val discountPercentage: BigDecimal,
    val quantity: Long
)