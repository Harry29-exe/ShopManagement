package com.wojcikk.shopmanagementapi.invoices.sales.domain

import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.invoices.purchase.domain.PurchaseInvoice
import com.wojcikk.shopmanagementapi.invoices.sales.dto.NewSoldItemDTO
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.sales.repository.SalesInvoiceRepo
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import com.wojcikk.shopmanagementapi.utils.Wrapper
import com.wojcikk.shopmanagementapi.utils.secure.hasAnyRole
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sales_invoices")
class SalesInvoice(
    entityId: Long,
    sellerId: Long,
    issueDate: Date,
    invoiceItems: List<NewSoldItemDTO>,
    productRepo: ProductRepo

) {

    @Id
    @GeneratedValue
    val id: Long = 0

    @OneToOne
    @JoinColumn(name = "correction_id", insertable = false, updatable = false)
    private var correction: SalesInvoice? = null

    @Column(name = "correction_id")
    private var correctionId: Long? = null

    @Column(nullable = false, name = "is_payed")
    private var payed = false

    @Column(nullable = false)
    val issueDate = issueDate

    @OneToMany(mappedBy = "invoice", cascade = [CascadeType.ALL])
    private val items: MutableSet<SoldItem> = invoiceItems
        .map { item -> SoldItem(this, item, productRepo) }
        .toMutableSet()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", insertable = false, updatable = false, nullable = false)
    private lateinit var seller: UserEntity

    @Column(name = "seller_id")
    private val sellerId = sellerId

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id", insertable = false, updatable = false, nullable = false)
    private lateinit var entity: BusinessEntity

    @Column(name = "entity_id")
    private val entityId = entityId

    fun createCorrection(
        correctionIssueDate: Date,
        items: List<NewSoldItemDTO>,
        productRepo: ProductRepo,
        salesInvoiceRepo: SalesInvoiceRepo
    ): SalesInvoice {

        for (item in this.items) {
            item.invalidate()
        }

        val correction = SalesInvoice(
            entityId, sellerId, correctionIssueDate,
            items, productRepo
        )
        val savedCorrection = salesInvoiceRepo.save(correction)

        this.correctionId = savedCorrection.correctionId
        this.correction = correction
        salesInvoiceRepo.save(this)

        return correction
    }

    fun markAsPayed() {
        payed = true
    }

    fun toDTO(): SalesInvoiceDTO = SalesInvoiceDTO(
        id,
        correctionId,
        "${seller.name} ${seller.surname} ${seller.id}",
        entity.toDTO(),
        issueDate,
        payed,
        items.map { item -> item.toDTO() }
    )

    companion object {

        private val CREATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN, Role.SELLER)
        private val READ = Role.ALL
        private val UPDATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)
        private val DELETE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)

        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(PurchaseInvoice::class.java, "id", id)
        }

        val canCreate: Wrapper = hasAnyRole(*CREATE)

        val canRead: Wrapper = hasAnyRole(*READ)

        val canUpdate = hasAnyRole(*UPDATE)

        val canDelete = hasAnyRole(*DELETE)
    }

}
