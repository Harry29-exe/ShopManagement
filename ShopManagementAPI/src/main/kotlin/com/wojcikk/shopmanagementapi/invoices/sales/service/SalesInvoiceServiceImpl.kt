package com.wojcikk.shopmanagementapi.invoices.sales.service

import com.wojcikk.shopmanagementapi.invoices.sales.domain.SalesInvoice
import com.wojcikk.shopmanagementapi.invoices.sales.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.sales.repository.SalesInvoiceRepo
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.utils.plus
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.validation.validate
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class SalesInvoiceServiceImpl(
    private val salesInvoiceRepo: SalesInvoiceRepo,
    private val productRepo: ProductRepo
) : SalesInvoiceService {

    override fun getAll(): List<SalesInvoiceDTO> = isAuthenticated {
        salesInvoiceRepo
            .findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): SalesInvoiceDTO = wrap(isAuthenticated)
    {
        salesInvoiceRepo
            .findByIdOrNull(id)
            ?.toDTO()
            ?: throw SalesInvoice.notExistWith(id)
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    override fun create(command: CreateSalesInvoice) = wrap(
        SalesInvoice.canCreate + validate(command)
    ) {
        val newInvoice = SalesInvoice(
            command.businessEntityId,
            command.sellerId,
            command.issuedAt,
            command.products,
            productRepo
        )

        salesInvoiceRepo
            .save(newInvoice)

        Unit
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    override fun createCorrection(command: CreateSalesInvoiceCorrection) = wrap(SalesInvoice.canUpdate)
    {
        val invoice = salesInvoiceRepo
            .findByIdOrNull(command.invoiceId)
            ?: throw SalesInvoice.notExistWith(command.invoiceId)

        invoice
            .createCorrection(
                command.correctionIssueDate,
                command.items,
                productRepo, salesInvoiceRepo
            )

        Unit
    }

    override fun markAsPayed(invoiceId: Long) = wrap(SalesInvoice.canUpdate)
    {
        val invoice = salesInvoiceRepo
            .findByIdOrNull(invoiceId)
            ?: throw SalesInvoice.notExistWith(invoiceId)

        invoice.markAsPayed()
        salesInvoiceRepo.save(invoice)

        Unit
    }
}
