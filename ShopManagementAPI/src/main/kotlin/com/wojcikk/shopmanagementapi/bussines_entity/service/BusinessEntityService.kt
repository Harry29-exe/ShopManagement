package com.wojcikk.shopmanagementapi.bussines_entity.service

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO

interface BusinessEntityService {

    fun getAll(): List<BusinessEntityDTO>

    fun get(id: Long): BusinessEntityDTO

    fun create(command: CreateBusinessEntity)

}

class CreateBusinessEntity(
    val name: String,
    val nip: String,
    val countryCode: String,
    val city: String,
    val email: String?,
    val phoneNumber: String?
)