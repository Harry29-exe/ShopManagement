package com.wojcikk.shopmanagementapi.invoices.repository

import com.wojcikk.shopmanagementapi.invoices.domain.SalesInvoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.Entity

@Repository
interface SalesInvoiceRepo : JpaRepository<SalesInvoice, Long> {

    fun findByPubId(pubId: UUID): SalesInvoice?

}