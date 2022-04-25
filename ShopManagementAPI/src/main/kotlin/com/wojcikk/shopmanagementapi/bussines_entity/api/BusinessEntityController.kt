package com.wojcikk.shopmanagementapi.bussines_entity.api

import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.bussines_entity.service.BusinessEntityService
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.web.bind.annotation.RestController

@RestController
class BusinessEntityController(
    private val businessEntityService: BusinessEntityService
) : BusinessEntityAPI {

    override fun getAll(): List<BusinessEntityDTO> = wrap(BusinessEntity.canRead) {
        businessEntityService.getAll()
    }

    override fun get(id: Long): BusinessEntityDTO = wrap(BusinessEntity.canRead) {
        businessEntityService.get(id)
    }

    override fun create(request: CreateBusinessEntityRequest) = wrap(BusinessEntity.canCreate) {
        businessEntityService.create(request)
    }
}