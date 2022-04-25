package com.wojcikk.shopmanagementapi.bussines_entity.service

import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.bussines_entity.repository.BusinessEntityRepo
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BusinessEntityServiceImpl(
    private val businessEntityRepo: BusinessEntityRepo
) : BusinessEntityService {

    override fun getAll(): List<BusinessEntityDTO> = wrap(BusinessEntity.canRead) {
        businessEntityRepo.findAll()
            .map { it.toDTO() }
    }

    override fun get(id: Long): BusinessEntityDTO = wrap(BusinessEntity.canRead) {
        businessEntityRepo.findByIdOrNull(id)
            ?.toDTO()
            ?: throw BusinessEntity.notExistWith(id)
    }

    override fun create(command: CreateBusinessEntity) = wrap(BusinessEntity.canCreate) {
        val entity = BusinessEntity(
            command.name,
            command.nip,
            command.countryCode,
            command.city,
            command.email,
            command.phoneNumber
        )

        businessEntityRepo.save(entity)
        Unit
    }
}