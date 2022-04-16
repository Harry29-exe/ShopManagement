package com.wojcikk.shopmanagementapi.invoices.sales

import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.utils.Wrapper
import com.wojcikk.shopmanagementapi.utils.secure.hasAnyRole

object SalesInvoicePermissions {

    val CREATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN, Role.SELLER)

    val hasCreateRoles: Wrapper = hasAnyRole(*CREATE)

    val READ = Role.ALL

    val UPDATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)

    val DELETE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)


}