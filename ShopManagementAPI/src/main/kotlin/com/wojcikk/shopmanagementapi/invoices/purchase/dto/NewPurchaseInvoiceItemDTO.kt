package com.wojcikk.shopmanagementapi.invoices.purchase.dto

import java.math.BigDecimal

class NewPurchaseInvoiceItemDTO(
    val itemId: Long,
    val quantity: Long,
    val price: BigDecimal?,
    val taxRate: BigDecimal?,
    val discount: BigDecimal?
)