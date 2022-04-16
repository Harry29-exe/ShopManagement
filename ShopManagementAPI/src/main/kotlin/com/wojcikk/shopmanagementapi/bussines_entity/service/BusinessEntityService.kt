package com.wojcikk.shopmanagementapi.bussines_entity.service

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO

interface BusinessEntityService {

    fun get(id: Long): BusinessEntityDTO

}