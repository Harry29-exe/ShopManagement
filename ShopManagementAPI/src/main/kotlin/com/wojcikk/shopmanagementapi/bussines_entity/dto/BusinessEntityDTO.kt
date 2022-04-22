package com.wojcikk.shopmanagementapi.bussines_entity.dto

class BusinessEntityDTO(
    val id: Long,
    val entityName: String,
    val nip: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null
)