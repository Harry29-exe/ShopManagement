package com.wojcikk.shopmanagementapi.invoices.purchase.service

import com.wojcikk.shopmanagementapi.invoices.purchase.domain.PurchaseInvoice
import com.wojcikk.shopmanagementapi.invoices.purchase.dto.PurchaseInvoiceDTO
import com.wojcikk.shopmanagementapi.invoices.purchase.repository.PurchaseInvoiceRepo
import com.wojcikk.shopmanagementapi.item.repository.ProductRepo
import com.wojcikk.shopmanagementapi.utils.secure.isAuthenticated
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class PurchaseInvoiceServiceImpl(
    private val purchaseInvoiceRepo: PurchaseInvoiceRepo,
    private val productRepo: ProductRepo
) : PurchaseInvoiceService {

    override fun getAll(): List<PurchaseInvoiceDTO> = wrap(isAuthenticated)
    {
        purchaseInvoiceRepo.findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): PurchaseInvoiceDTO = wrap(isAuthenticated)
    {
        purchaseInvoiceRepo.findByIdOrNull(id)
            ?.toDTO()
            ?: throw PurchaseInvoice.notExistWith(id)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    override fun create(command: CreatePurchaseInvoice) = wrap(isAuthenticated)
    {
        val newInvoice = PurchaseInvoice(
            command.businessEntityId,
            command.purchaserId,
            command.issuedAt,
            command.items,
            productRepo
        )

        purchaseInvoiceRepo
            .save(newInvoice)

        Unit
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    override fun createCorrection(command: CreatePurchaseInvoiceCorrection) = wrap(isAuthenticated)
    {
        val invoice = purchaseInvoiceRepo
            .findByIdOrNull(command.invoiceId)
            ?: throw PurchaseInvoice.notExistWith(command.invoiceId)
        val correction = invoice
            .createCorrection(command.correctionIssueDate, command.items, productRepo)

        purchaseInvoiceRepo.save(correction)

        Unit
    }

    //todo change permission
    override fun delete(id: Long) = wrap(isAuthenticated) {
        purchaseInvoiceRepo.deleteById(id)
    }
}