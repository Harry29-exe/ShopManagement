package com.wojcikk.shopmanagementapi.invoices.sales.repository

import com.wojcikk.shopmanagementapi.invoices.purchase.domain.PurchaseInvoice
import com.wojcikk.shopmanagementapi.invoices.sales.domain.SalesInvoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesInvoiceRepo : JpaRepository<SalesInvoice, Long> {

}