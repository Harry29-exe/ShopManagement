package com.wojcikk.shopmanagementapi.invoices.dto

import java.util.UUID

class BusinessEntityDTO(
    val pubId: UUID,
    val nip: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null
)