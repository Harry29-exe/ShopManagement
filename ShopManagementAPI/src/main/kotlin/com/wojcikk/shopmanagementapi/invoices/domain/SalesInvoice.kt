package com.wojcikk.shopmanagementapi.invoices.domain

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_id")
    private val entity: BusinessEntity,
    @Column(nullable = false)
    private val issueDate: Date,
    @Column(nullable = false)
    private val payed: Boolean,
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private val issuer: Seller,
    invoiceProducts: List<ProductWithQuantity>,
    productRepo: ProductRepo
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

    @Column(nullable = false, updatable = false, unique = true)
    private val pubId: UUID = UUID.randomUUID()

    @OneToMany(mappedBy = "invoice")
    private val products: MutableSet<SalesInvoiceProduct> = HashSet()

    init {
        for (productInInvoice in invoiceProducts) {
            this.products.add(SalesInvoiceProduct(
                productRepo.getById(productInInvoice.productPubId),
                productInInvoice.quantity,
                this
            ))
        }
    }


}