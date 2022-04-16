package com.wojcikk.shopmanagementapi.bussines_entity.repository

import com.wojcikk.shopmanagementapi.bussines_entity.domain.BusinessEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BusinessEntityRepo : JpaRepository<BusinessEntity, Long>