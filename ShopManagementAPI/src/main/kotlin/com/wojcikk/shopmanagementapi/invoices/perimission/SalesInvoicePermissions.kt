package com.wojcikk.shopmanagementapi.invoices.perimission

import com.wojcikk.shopmanagementapi.user.domain.Role

object SalesInvoicePermissions {

    val CREATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN, Role.SELLER)

    val READ = Role.ALL

    val UPDATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)

    val DELETE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)


}