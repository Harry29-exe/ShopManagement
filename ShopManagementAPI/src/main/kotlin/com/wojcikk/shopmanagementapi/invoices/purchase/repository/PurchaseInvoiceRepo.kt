package com.wojcikk.shopmanagementapi.invoices.purchase.repository

import com.wojcikk.shopmanagementapi.invoices.purchase.domain.PurchaseInvoice
import org.springframework.data.jpa.repository.JpaRepository

interface PurchaseInvoiceRepo : JpaRepository<PurchaseInvoice, Long>