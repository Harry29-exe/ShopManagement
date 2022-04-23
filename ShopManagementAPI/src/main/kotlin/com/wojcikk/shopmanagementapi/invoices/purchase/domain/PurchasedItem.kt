package com.wojcikk.shopmanagementapi.invoices.purchase.domain

import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchasedItemDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceItemDTO
import com.wojcikk.shopmanagementapi.item.domain.Item
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "purchase_invoice_items")
class PurchasedItem(
    invoice: PurchaseInvoice,
    itemInfo: NewPurchasedItemDTO,
    productRepo: ProductRepo
) {

    @Column(name = "item_id")
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
    @JoinColumn(nullable = false, name = "purchase_invoice_id")
    private val invoice: PurchaseInvoice = invoice

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, nullable = false, name = "item_id")
    private lateinit var item: Item

    @Id
    @GeneratedValue
    private val id: Long = 0

    fun toDTO(): PurchaseInvoiceItemDTO = PurchaseInvoiceItemDTO(
        itemId,
        nameOnInvoice,
        quantity,
        price,
        taxRate,
        discountPercentage
    )

    fun invalidate() {
        item.decreaseQuantity(quantity)
        invalidated = true
    }

    init {
        val product = productRepo.findByIdOrNull(itemInfo.itemId)
            ?: throw Item.notExistWith(itemInfo.itemId)
        product.increaseQuantity(itemInfo.quantity)

        item = product
        nameOnInvoice = itemInfo.nameOnInvoice ?: product.codeName
        quantity = itemInfo.quantity
        price = itemInfo.price ?: product.getPriceAt(invoice.issueDate)
        taxRate = itemInfo.taxRate ?: product.taxRate
        discountPercentage = itemInfo.discount ?: BigDecimal.ZERO
    }

}