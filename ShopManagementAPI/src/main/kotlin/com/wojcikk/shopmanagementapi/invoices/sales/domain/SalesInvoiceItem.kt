package com.wojcikk.shopmanagementapi.invoices.sales.domain

import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceItemDTO
import com.wojcikk.shopmanagementapi.item.domain.Item
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "sales_invoice_items")
class SalesInvoiceItem(
    @Column(name = "item_id")
    private val itemId: Long,
    @Column(nullable = false, name = "name_on_invoice", length = 32)
    private val nameOnInvoice: String,
    @Column(nullable = false)
    private val quantity: Long,
    @Column(nullable = false)
    private val price: BigDecimal,
    @Column(nullable = false)
    private val taxRate: BigDecimal,
    @Column(nullable = false)
    private val discountPercentage: BigDecimal,
    @ManyToOne
    @JoinColumn(nullable = false, name = "invoice_id")
    private val invoice: SalesInvoice

) {

    @ManyToOne
    @JoinColumn(nullable = true, insertable = false, name = "item_id")
    private lateinit var item: Item

    @Id
    @GeneratedValue
    private val id: Long = 0

    fun toDTO(): SalesInvoiceItemDTO = SalesInvoiceItemDTO(
        itemId,
        nameOnInvoice,
        quantity,
        price,
        taxRate,
        discountPercentage
    )

}