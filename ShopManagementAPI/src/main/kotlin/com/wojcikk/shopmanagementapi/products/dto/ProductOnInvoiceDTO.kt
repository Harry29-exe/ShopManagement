package com.wojcikk.shopmanagementapi.products.dto

import java.math.BigDecimal

class ProductOnInvoiceDTO(
    val codeName: String,
    val priceOnInvoice: BigDecimal,
    val quantity: Int
)