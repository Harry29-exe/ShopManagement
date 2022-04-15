package com.wojcikk.shopmanagementapi.invoices.service.impl

import com.wojcikk.shopmanagementapi.invoices.repository.SalesInvoiceRepo
import com.wojcikk.shopmanagementapi.invoices.domain.SalesInvoice
import com.wojcikk.shopmanagementapi.invoices.dto.SalesInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.perimission.SalesInvoices
import com.wojcikk.shopmanagementapi.invoices.service.CreateSalesInvoice
import com.wojcikk.shopmanagementapi.invoices.service.SalesInvoiceService
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.utils.plus
import com.wojcikk.shopmanagementapi.utils.secure.hasAnyRole
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.validation.validate
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
class SalesInvoiceServiceImpl(
    private val salesInvoiceRepo: SalesInvoiceRepo,
    private val productRepo: ProductRepo
) : SalesInvoiceService {

    override fun getAll(): List<SalesInvoiceDTO> = isAuthenticated {
        salesInvoiceRepo
            .findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): SalesInvoiceDTO =
        isAuthenticated()
    {
        return salesInvoiceRepo
            .findByIdOrNull(id)
            ?.toDTO()
            ?:throw SalesInvoice.notExistWith(id)
    }

    override fun create(command: CreateSalesInvoice): SalesInvoiceDTO = wrap(
        SalesInvoices.hasCreateRoles + validate(command)
    ) {
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

        return newInvoice.toDTO()
        }
//        hasRole(*SalesInvoicePermissions.CREATE, () -> {areValid<SalesInvoiceDTO>(command)
//    {
//        val newInvoice = SalesInvoice(
//            command.businessEntityId,
//            command.sellerId,
//            command.issuedAt,
//            false,
//            command.products,
//            productRepo
//        )
//
//        salesInvoiceRepo
//            .save(newInvoice)
//
//        return newInvoice.toDTO()
//    }})


    override fun markAsPayed(invoiceId: Long): SalesInvoiceDTO = hasAnyRole(Role.ACCOUNTANT, Role.ADMIN) {
        val invoice = salesInvoiceRepo
            .findByIdOrNull(invoiceId)
            ?:throw SalesInvoice.notExistWith(invoiceId)

        invoice.markAsPayed()

        invoice.toDTO()
    }
}
