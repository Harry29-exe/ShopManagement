package com.wojcikk.shopmanagementapi.products.dto

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

class ProductDTO(
    val id: Long,
    val name: String,
    val codeName: String,
    val description: String,
    val currentPrice: BigDecimal,
    val priceHistory: List<ProductPriceDTO>
)

class ProductPriceDTO(
    val setDate: Date,
    val price: BigDecimal
)