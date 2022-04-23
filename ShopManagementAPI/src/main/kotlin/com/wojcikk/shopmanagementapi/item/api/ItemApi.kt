package com.wojcikk.shopmanagementapi.item.api

import com.wojcikk.shopmanagementapi.item.dto.ItemDTO
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RequestMapping("/items")
interface ItemApi {

    @GetMapping("/all")
    fun getAll(): List<ItemDTO>

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ItemDTO

    @PostMapping
    fun create(@RequestBody request: CreateItemRequest)

}

class CreateItemRequest(
    val name: String,
    val codeName: String?,
    val description: String?,
    val price: BigDecimal,
    val taxRate: BigDecimal
)