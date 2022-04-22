package com.wojcikk.shopmanagementapi.item.dto

import java.math.BigDecimal
import java.util.*

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