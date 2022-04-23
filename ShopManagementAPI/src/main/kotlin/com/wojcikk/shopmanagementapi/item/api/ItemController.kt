package com.wojcikk.shopmanagementapi.item.api

import com.wojcikk.shopmanagementapi.item.dto.ItemDTO
import com.wojcikk.shopmanagementapi.item.service.CreateItem
import com.wojcikk.shopmanagementapi.item.service.ItemService
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    private val itemService: ItemService
) : ItemApi {

    override fun getAll(): List<ItemDTO> = wrap(isAuthenticated) {
        itemService.getAll()
    }

    override fun get(id: Long): ItemDTO = wrap(isAuthenticated) {
        itemService.get(id)
    }

    override fun create(request: CreateItemRequest) = wrap(isAuthenticated) {
        itemService.create(
            CreateItem(
                request.name,
                request.codeName ?: request.name,
                request.description ?: request.name,
                request.taxRate,
                request.price
            )
        )
    }
}