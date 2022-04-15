package com.wojcikk.shopmanagementapi.invoices.domain

import com.wojcikk.shopmanagementapi.item.domain.Product
import com.wojcikk.shopmanagementapi.item.dto.ProductOnInvoiceDTO
import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "purchase_invoice_product")
class SalesInvoiceItem(
    @Column(nullable = false)
    private val nameOnInvoice: String,
    @Column(nullable = false)
    private val quantity: Int,
    @Column(nullable = false)
    private val price: BigDecimal,
    @Column(nullable = false)
    private val taxRate: BigDecimal,
    @ManyToOne
    @JoinColumn(nullable = false, name = "invoice_id")
    private val invoice: SalesInvoice,

) {

    @Column(nullable = false)
    private val discountPercentage: BigDecimal?

    init {

    }

    @ManyToOne
    @JoinColumn(nullable = true, name = "product_id")
    private val product: Product?

    init {

    }

    @Id
    @GeneratedValue
    private val id: Long = 0

    fun toDTO(invoiceIssueDate: Date): ProductOnInvoiceDTO =
        product.toProductOnInvoice(
            invoiceIssueDate,
            discountPercentage,
            quantity
        )

}