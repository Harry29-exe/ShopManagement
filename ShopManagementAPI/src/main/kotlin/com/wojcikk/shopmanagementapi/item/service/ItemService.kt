package com.wojcikk.shopmanagementapi.item.service

import com.wojcikk.shopmanagementapi.item.dto.ItemDTO
import java.math.BigDecimal

interface ItemService {

    fun getAll(): List<ItemDTO>

    fun get(id: Long): ItemDTO

    fun create(command: CreateItem)

}

class CreateItem(
    val name: String,
    val codeName: String,
    val description: String,
    val taxRate: BigDecimal,
    val price: BigDecimal
)