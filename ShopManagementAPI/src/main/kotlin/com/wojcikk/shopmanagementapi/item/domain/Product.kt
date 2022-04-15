package com.wojcikk.shopmanagementapi.item.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.item.dto.ProductDTO
import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import com.wojcikk.shopmanagementapi.item.dto.ProductPriceDTO
import java.math.BigDecimal
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@Entity
@Table(name = "products")
class Product(

    @Column(nullable = false)
    private val name: String,
    @Column(nullable = false, unique = true)
    private val codeName: String,
    @Column(nullable = false)
    private val taxRate: BigDecimal,
    @Column(nullable = false, length = 400)
    private var description: String,
    @Column(nullable = false)
    private var currentPrice: BigDecimal

) {

    @Id
    @GeneratedValue
    val id: Long = 0

    @OneToMany(mappedBy = "product",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    val priceHistory: MutableSet<Price> = HashSet(1)

    init {
        val currentPrice = Price(currentPrice, Date(), this)
        priceHistory.add(currentPrice)
    }


    fun updatePrice(price: BigDecimal) {
        currentPrice = price
        priceHistory.add(Price(price, Date(), this))
    }

    fun toDTO(): ProductDTO {
        return ProductDTO(
            id,
            name,
            codeName,
            description,
            currentPrice,
            mapPriceHistoryToDTO()
        )
    }

    fun toProductOnInvoice(invoiceIssueDate: Date, discountPercentage: BigDecimal, quantity: Int): ProductOnInvoiceDTO {
        return ProductOnInvoiceDTO(
            codeName,
            getPriceAt(invoiceIssueDate),
            taxRate,
            discountPercentage,
            quantity
        )
    }

    fun getPriceAt(date: Date): BigDecimal {
        val iterator = priceHistory.iterator()
        var priceAtDate: Price = iterator.next()

        for (price in iterator) {
            if (price.setDate.before(date)
                && price.setDate.after(priceAtDate.setDate)) {
                priceAtDate = price
            }
        }

        return priceAtDate.price
    }

    private fun mapPriceHistoryToDTO(): List<ProductPriceDTO> {
        return priceHistory
            .map { ProductPriceDTO(it.setDate, it.price) }
            .toCollection(ArrayList())
    }

    companion object {
        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(
                Product::class.java,
                "id",
                id
            )
        }
    }

}