package com.wojcikk.shopmanagementapi.invoices.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.products.domain.Product
import com.wojcikk.shopmanagementapi.products.value.ProductWithQuantity
import com.wojcikk.shopmanagementapi.products.repository.ProductRepo
import com.wojcikk.shopmanagementapi.seller.domain.Seller
import java.util.Date
import java.util.UUID
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
    invoiceProducts: List<ProductWithQuantity>,
    productRepo: ProductRepo
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

    @OneToMany(mappedBy = "invoice")
    private val products: MutableSet<SalesInvoiceProduct> = HashSet()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id", insertable = false)
    private lateinit var issuer: Seller

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id", insertable = false)
    private lateinit var entity: BusinessEntity

    init {
        for (productInInvoice in invoiceProducts) {
            val product = productRepo
                .findByPubId(productInInvoice.productPubId)
                ?:throw Product.notExistWith(productInInvoice.productPubId)

            this.products.add(SalesInvoiceProduct(
                product,
                productInInvoice.quantity,
                this
            ))
        }
    }

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