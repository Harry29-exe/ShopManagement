package com.wojcikk.shopmanagementapi.invoices.service

import com.wojcikk.shopmanagementapi.invoices.dto.PurchaseInvoiceDTO

interface PurchaseInvoiceService {

    fun getAll(): List<PurchaseInvoiceDTO>

    fun get(id: Long): PurchaseInvoiceDTO

    fun create(command: CreatePurchaseInvoice): PurchaseInvoiceDTO

    fun createCorrection(command: CreatePurchaseInvoiceCorrection): PurchaseInvoiceDTO

    fun delete(id: Long)

}

class CreatePurchaseInvoice(

)

class CreatePurchaseInvoiceCorrection(

)