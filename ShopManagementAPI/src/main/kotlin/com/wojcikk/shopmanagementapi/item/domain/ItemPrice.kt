package com.wojcikk.shopmanagementapi.item.domain

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
@Table(name = "item_price_history")
class ItemPrice(
    @Column(nullable = false)
    val price: BigDecimal,
    @Column(nullable = false)
    val setDate: Date,
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    val item: Item
) : Comparable<ItemPrice> {

    @Id
    @GeneratedValue
    private val id: Long = 0

    override fun compareTo(other: ItemPrice): Int {
        return this.setDate.compareTo(other.setDate)
    }
}