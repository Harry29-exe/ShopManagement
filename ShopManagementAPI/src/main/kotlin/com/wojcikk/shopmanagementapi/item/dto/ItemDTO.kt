package com.wojcikk.shopmanagementapi.item.dto

import java.math.BigDecimal
import java.util.*

class ItemDTO(
    val id: Long,
    val name: String,
    val codeName: String,
    val description: String,
    val currentPrice: BigDecimal,
    val priceHistory: List<ItemPriceDTO>
)

class ItemPriceDTO(
    val setDate: Date,
    val price: BigDecimal
)