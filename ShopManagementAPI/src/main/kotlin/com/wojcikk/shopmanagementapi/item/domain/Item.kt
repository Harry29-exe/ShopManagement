package com.wojcikk.shopmanagementapi.item.domain

import com.wojcikk.shopmanagementapi.exception.item.NotEnoughItemsException
import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.item.dto.ItemDTO
import com.wojcikk.shopmanagementapi.item.dto.ItemPriceDTO
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "items")
class Item(

    @Column(nullable = false)
    val name: String,
    @Column(nullable = false, unique = true)
    val codeName: String,
    @Column(nullable = false)
    val taxRate: BigDecimal,
    description: String,
    currentPrice: BigDecimal

) {
    @Id
    @GeneratedValue
    val id: Long = 0

    @Column(nullable = false)
    var currentPrice = currentPrice
        private set

    @OneToMany(mappedBy = "item", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val priceHistory: MutableSet<ItemPrice> = HashSet(1)

    init {
        val priceInHistory = ItemPrice(currentPrice, Date(), this)
        priceHistory.add(priceInHistory)
    }

    @Column(nullable = false, length = 400)
    var description = description
        private set

    @Column(nullable = false, name = "quantity_in_stock")
    var quantityInStock: Long = 0

    fun updatePrice(price: BigDecimal) {
        currentPrice = price
        priceHistory.add(ItemPrice(price, Date(), this))
    }

    fun decreaseQuantity(decreaseBy: Long) {
        if (quantityInStock < decreaseBy) {
            throw NotEnoughItemsException()
        }
        this.quantityInStock -= decreaseBy
    }

    fun increaseQuantity(increaseBy: Long) {
        this.quantityInStock += increaseBy
    }

    fun getPriceAt(date: Date): BigDecimal {
        val iterator = priceHistory.iterator()
        var priceAtDate: ItemPrice = iterator.next()

        for (price in iterator) {
            if (price.setDate.before(date)
                && price.setDate.after(priceAtDate.setDate)
            ) {
                priceAtDate = price
            }
        }

        return priceAtDate.price
    }

    fun toDTO(): ItemDTO {
        return ItemDTO(
            id,
            name,
            codeName,
            description,
            currentPrice,
            mapPriceHistoryToDTO()
        )
    }

    private fun mapPriceHistoryToDTO(): List<ItemPriceDTO> {
        return priceHistory
            .map { ItemPriceDTO(it.setDate, it.price) }
            .toCollection(ArrayList())
    }

    companion object {
        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(
                Item::class.java,
                "id",
                id
            )
        }
    }

}