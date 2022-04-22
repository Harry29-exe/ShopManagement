package com.wojcikk.shopmanagementapi.invoices.purchase.api

import com.wojcikk.shopmanagementapi.invoices.purchase.dto.NewPurchasedItemDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import org.springframework.web.bind.annotation.*
import java.util.*

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
        @RequestBody request: CreatePurchaseInvoiceCorrectionRequest
    )

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long)

}

class CreatePurchaseInvoiceRequest(
    val purchaserId: Long,
    val businessEntityId: Long,
    val issuedAt: Date,
    val items: List<NewPurchasedItemDTO>
)

class CreatePurchaseInvoiceCorrectionRequest(
    val correctionIssueDate: Date,
    val items: List<NewPurchasedItemDTO>
)
