package com.wojcikk.shopmanagementapi.invoices.dto

import java.util.UUID

class BusinessEntityDTO(
    val id: Long,
    val entityName: String,
    val nip: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null
)