package com.wojcikk.shopmanagementapi.item.service

import com.wojcikk.shopmanagementapi.item.domain.Item
import com.wojcikk.shopmanagementapi.item.dto.ItemDTO
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(
    private val productRepo: ProductRepo
) : ItemService {

    override fun getAll(): List<ItemDTO> = wrap(isAuthenticated)
    {
        productRepo
            .findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): ItemDTO = wrap(isAuthenticated)
    {
        productRepo
            .findByIdOrNull(id)
            ?.toDTO()
            ?: throw Item.notExistWith(id)
    }

    override fun create(command: CreateItem) = wrap(isAuthenticated)
    {
        val item = Item(
            command.name, command.codeName, command.taxRate,
            command.description, command.price
        )

        productRepo.save(item)

        Unit
    }
}