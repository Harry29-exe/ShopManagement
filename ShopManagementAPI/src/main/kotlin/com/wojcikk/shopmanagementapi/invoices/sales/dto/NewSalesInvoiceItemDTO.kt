package com.wojcikk.shopmanagementapi.invoices.sales.dto

import java.math.BigDecimal

class NewSalesInvoiceItemDTO(
    val itemId: Long,
    val quantity: Long,
    val nameOnInvoice: String?,
    val price: BigDecimal?,
    val taxRate: BigDecimal?,
    val discount: BigDecimal?
)