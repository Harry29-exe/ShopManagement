package com.wojcikk.shopmanagementapi.invoices.api

import com.wojcikk.shopmanagementapi.invoices.dto.PurchaseInvoiceDTO
import org.springframework.web.bind.annotation.*

@RequestMapping("/purchase-invoices")
interface PurchaseInvoicesAPI {

    @GetMapping("/all")
    fun getAll(): List<PurchaseInvoiceDTO>

    @GetMapping("{id}")
    fun get(@PathVariable id: Long): PurchaseInvoiceDTO

    @PostMapping
    fun create(@RequestBody request: CreatePurchaseInvoiceRequest): PurchaseInvoiceDTO

    @PostMapping("/{id}/correction")
    fun createInvoiceCorrection(
        @PathVariable id: Long,
        @RequestBody request: CreateInvoiceCorrectionRequest)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long)

}

//todo
class CreatePurchaseInvoiceRequest(

)

//todo
class CreateInvoiceCorrectionRequest(

)