package com.wojcikk.shopmanagementapi.invoices.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/purchase-invoices")
interface PurchaseInvoices {

    @GetMapping("/all")
    fun getAllPurchaseInvoices()

}