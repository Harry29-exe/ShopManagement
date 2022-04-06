package com.wojcikk.shopmanagementapi.invoices.domain

import com.wojcikk.shopmanagementapi.products.domain.Product
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "purchase_invoice_product")
class SalesInvoiceProduct(
    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private val product: Product,
    @Column(nullable = false)
    private val quantity: Int,
    @ManyToOne
    @JoinColumn(nullable = false, name = "invoice_id")
    private val invoice: SalesInvoice
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

}