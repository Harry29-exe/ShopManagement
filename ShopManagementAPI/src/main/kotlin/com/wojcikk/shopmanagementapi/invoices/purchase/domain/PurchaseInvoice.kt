package com.wojcikk.shopmanagementapi.invoices.purchase.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchasedItemDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
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
    entityId: Long,
    purchaserId: Long,
    issueDate: Date,
    invoiceItems: List<NewPurchasedItemDTO>,
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

    @Column(nullable = false)
    val issueDate: Date = issueDate

    @Column(nullable = false, updatable = false, name = "purchaser_id")
    private val purchaserId = purchaserId

    @Column(nullable = false, updatable = false, name = "entity_id")
    private val entityId = entityId

    @Column(nullable = false, name = "is_payed")
    private var payed = false

    @OneToMany(mappedBy = "invoice", cascade = [CascadeType.ALL])
    private val items: MutableSet<PurchaseInvoiceItem> = invoiceItems
        .map { item -> PurchaseInvoiceItem(this, item, productRepo) }
        .toMutableSet()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchaser_id", insertable = false, updatable = false)
    private lateinit var purchaser: UserEntity

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id", insertable = false, updatable = false)
    private lateinit var entity: BusinessEntity

    fun createCorrection(
        correctionIssueDate: Date,
        items: List<NewPurchasedItemDTO>,
        productRepo: ProductRepo
    ): PurchaseInvoice {

        val correction = PurchaseInvoice(
            entityId, purchaserId, correctionIssueDate,
            items, productRepo
        )

        this.correction = correction

        return correction;
    }

    fun markAsPayed() {
        payed = true
    }

    fun toDTO(): PurchaseInvoiceDTO = PurchaseInvoiceDTO(
        id,
        correctionId,
        "${purchaser.name} ${purchaser.surname} ${purchaser.id}",
        entity.toDTO(),
        issueDate,
        payed,
        items.map { item -> item.toDTO() }
    )

    companion object {
        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(PurchaseInvoice::class.java, "id", id)
        }
    }

}
