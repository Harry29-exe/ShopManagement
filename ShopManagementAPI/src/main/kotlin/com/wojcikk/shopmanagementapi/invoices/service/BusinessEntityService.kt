package com.wojcikk.shopmanagementapi.invoices.service

import com.wojcikk.shopmanagementapi.invoices.dto.BusinessEntityDTO

interface BusinessEntityService {

    fun get(id: Long): BusinessEntityDTO

}