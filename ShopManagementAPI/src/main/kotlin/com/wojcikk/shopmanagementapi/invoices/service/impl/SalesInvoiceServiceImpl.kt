package com.wojcikk.shopmanagementapi.invoices.service.impl

import com.wojcikk.shopmanagementapi.invoices.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.invoices.repository.SalesInvoiceRepo
import com.wojcikk.shopmanagementapi.invoices.domain.SalesInvoice
import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.perimission.SalesInvoicePermissions
import com.wojcikk.shopmanagementapi.invoices.repository.BusinessEntityRepo
import com.wojcikk.shopmanagementapi.invoices.service.CreateSalesInvoice
import com.wojcikk.shopmanagementapi.invoices.service.SalesInvoiceService
import com.wojcikk.shopmanagementapi.products.repository.ProductRepo
import com.wojcikk.shopmanagementapi.utils.secure.hasRole
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import java.util.*

class SalesInvoiceServiceImpl(
    private val salesInvoiceRepo: SalesInvoiceRepo,
    private val productRepo: ProductRepo
) : SalesInvoiceService {

    override fun getAll(): List<SalesInvoiceDTO> = isAuthenticated {
        salesInvoiceRepo
            .findAll()
            .map { it.toDTO() }
    }

    override fun getByPubId(pubId: UUID): SalesInvoiceDTO = isAuthenticated {
        salesInvoiceRepo
            .findByPubId(pubId)
            ?.toDTO()
            ?:throw SalesInvoice.notExistWith(pubId)
    }

    override fun create(command: CreateSalesInvoice) = hasRole(*SalesInvoicePermissions.CREATE){
        val newInvoice = SalesInvoice(
            command.businessEntityId,
            command.sellerId,
            command.issuedAt,
            false,
            command.products,
            productRepo
        )

        salesInvoiceRepo
            .save(newInvoice)

        Unit
    }

    override fun markAsPayed(invoicePubId: UUID): SalesInvoiceDTO {
        TODO("Not yet implemented")
    }
}