package com.wojcikk.shopmanagementapi.invoices.repository

import com.wojcikk.shopmanagementapi.invoices.domain.BusinessEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BusinessEntityRepo : JpaRepository<BusinessEntity, Long> {

    fun findByPubId(pubId: UUID): BusinessEntity?


}