package com.wojcikk.shopmanagementapi.invoices.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//todo wrócić do sobótki i invoice'ów
@Entity
class PurchaseInvoice(
    val total: BigDecimal
) {

    @Id
    @GeneratedValue
    private val id: Long = 0

}