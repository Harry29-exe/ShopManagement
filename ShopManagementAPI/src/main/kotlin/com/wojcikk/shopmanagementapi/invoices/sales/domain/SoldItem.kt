package com.wojcikk.shopmanagementapi.invoices.sales.domain

import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSoldItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SoldItemDTO
import com.wojcikk.shopmanagementapi.item.domain.Item
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "sales_invoice_items")
internal class SoldItem(
    invoice: SalesInvoice,
    itemInfo: NewSoldItemDTO,
    productRepo: ProductRepo

) {


    @Column(name = "item_id", nullable = false)
    private val itemId: Long = itemInfo.itemId

    @Column(name = "invalidated", nullable = false)
    private var invalidated: Boolean = false

    @Column(nullable = false, name = "name_on_invoice", length = 32)
    private val nameOnInvoice: String

    @Column(nullable = false)
    private val quantity: Long

    @Column(nullable = false)
    private val price: BigDecimal

    @Column(nullable = false)
    private val taxRate: BigDecimal

    @Column(nullable = false, name = "discount")
    private val discountPercentage: BigDecimal

    @ManyToOne
    @JoinColumn(nullable = false, name = "sales_invoice_id")
    private val invoice: SalesInvoice = invoice

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "item_id")
    private lateinit var item: Item

    @Id
    @GeneratedValue
    private val id: Long = 0

    fun toDTO(): SoldItemDTO = SoldItemDTO(
        itemId,
        nameOnInvoice,
        quantity,
        price,
        taxRate,
        discountPercentage
    )

    fun invalidate() {
        item.increaseQuantity(quantity)
        invalidated = true
    }

    init {
        val product = productRepo.findByIdOrNull(itemInfo.itemId)
            ?: throw Item.notExistWith(itemInfo.itemId)
        product.decreaseQuantity(itemInfo.quantity)

        nameOnInvoice = itemInfo.nameOnInvoice ?: product.codeName
        quantity = itemInfo.quantity
        price = itemInfo.price ?: product.getPriceAt(invoice.issueDate)
        taxRate = itemInfo.taxRate ?: product.taxRate
        discountPercentage = itemInfo.discount ?: BigDecimal.ZERO
    }
}