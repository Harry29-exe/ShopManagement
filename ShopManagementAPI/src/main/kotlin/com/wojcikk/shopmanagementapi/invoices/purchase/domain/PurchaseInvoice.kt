package com.wojcikk.shopmanagementapi.invoices.purchase.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSalesInvoiceItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.item.domain.Item
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

//todo wrócić do sobótki i invoice'ów
@Entity
@Table(name = "purchase_invoices")
class PurchaseInvoice(
    @Column(nullable = false, updatable = false, name = "entity_id")
    private val entityId: Long,
    @Column(nullable = false, updatable = false, name = "purchaser_id")
    private val purchaserId: Long,
    @Column(nullable = false)
    private val issueDate: Date,
    @Column(nullable = false, name = "is_payed")
    private var payed: Boolean,
    invoiceItems: List<NewSalesInvoiceItemDTO>,
    productRepo: ProductRepo

) {

    @Id
    @GeneratedValue
    private val id: Long = 0

    @OneToOne
    @JoinColumn(name = "correction_id", insertable = false, updatable = false)
    private var correction: PurchaseInvoice? = null

    @Column(name = "correction_id")
    private var correctionId: Long? = null

    @OneToMany(mappedBy = "invoice", cascade = [CascadeType.ALL])
    private val items: MutableSet<PurchaseInvoiceItem> = invoiceItems.map { invoiceItem ->
        val item = productRepo
            .findByIdOrNull(invoiceItem.itemId)
            ?:throw Item.notExistWith(invoiceItem.itemId)

        item.decreaseQuantity(invoiceItem.quantity)

        createInvoiceItem(invoiceItem, item)
    }.toMutableSet()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchaser_id", insertable = false, updatable = false)
    private lateinit var purchaser: UserEntity

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id", insertable = false, updatable = false)
    private lateinit var entity: BusinessEntity

    fun createCorrection(
        correctionIssueDate: Date,
        items: List<NewSalesInvoiceItemDTO>,
        productRepo: ProductRepo
    ) {
        val correction = PurchaseInvoice(
            entityId, purchaserId, correctionIssueDate, payed,
            items, productRepo
        )
    }

    fun markAsPayed() {
        payed = true
    }

    fun toDTO(): PurchaseInvoiceDTO = PurchaseInvoiceDTO(
        id,
        correctionId,
        entity.toDTO(),
        issueDate,
        payed,
        items.map { item -> item.toDTO() }
    )


    private fun createInvoiceItem(newItemInfo: NewSalesInvoiceItemDTO, item: Item)
    = PurchaseInvoiceItem(
        item.id,
        newItemInfo.nameOnInvoice?:item.codeName,
        newItemInfo.quantity,
        newItemInfo.price?:item.getPriceAt(issueDate),
        newItemInfo.taxRate?:item.taxRate,
        newItemInfo.discount?: BigDecimal.ZERO,
        this
    )

    companion object {
        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(PurchaseInvoice::class.java, "id", id)
        }
    }

}
