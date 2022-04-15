package com.wojcikk.shopmanagementapi.invoices.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.item.domain.Product
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.item.value.NewInvoiceItem
import com.wojcikk.shopmanagementapi.seller.domain.Seller
import org.springframework.data.repository.findByIdOrNull
import java.util.Date
import javax.persistence.*

//todo wrócić do sobótki i invoice'ów
@Entity
@Table(name = "purchase_invoices")
class SalesInvoice(
    @Column(nullable = false, updatable = false, name = "entity_id")
    private val entityId: Long,
    @Column(nullable = false, updatable = false, name = "seller_id")
    private val sellerId: Long,
    @Column(nullable = false)
    private val issueDate: Date,
    @Column(nullable = false)
    private var payed: Boolean,
    invoiceItems: List<NewInvoiceItem>,
    productRepo: ProductRepo
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

    @OneToOne
    @JoinColumn(name = "correction_id", insertable = false)
    private var correction: SalesInvoice? = null

    @Column(name = "correction_id")
    private var correctionId: Long? = null

    @OneToMany(mappedBy = "invoice")
    private val products: MutableSet<SalesInvoiceItem> = HashSet()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", insertable = false)
    private lateinit var issuer: Seller

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id", insertable = false)
    private lateinit var entity: BusinessEntity

    init {
        for (invoiceItem in invoiceItems) {
            if (invoiceItem.productId != null) {
                val product = productRepo
                    .findByIdOrNull(invoiceItem.productId)
                    ?:throw Product.notExistWith(invoiceItem.productId)

                this.products.add(SalesInvoiceItem(
                    product,
                    invoiceItem.quantity,
                    invoiceItem.discount,
                    this
                ))
            }



        }
    }

    fun createCorrection()

    fun markAsPayed() {
        payed = true
    }

    fun toDTO(): SalesInvoiceDTO = SalesInvoiceDTO(
        id,

        entity.toDTO(),
        issueDate,
        payed,
        products.map { it.toDTO(issueDate) }
    )

    companion object {
        fun notExistWith(id: Long): ResourceNotExistException {
            return ResourceNotExistException(SalesInvoice::class.java, "id", id)
        }
    }


}