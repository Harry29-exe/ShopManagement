package com.wojcikk.shopmanagementapi.bussines_entity.domain

import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "business_entities")
class BusinessEntity(
    @Column(name = "name")
    val entityName: String
) {

    @Column(unique = true, updatable = false)
    val nip: String? = null
    @Column
    val email: String? = null
    @Column
    val phoneNumber: String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    fun toDTO(): BusinessEntityDTO = BusinessEntityDTO(
        id,
        entityName,
        nip,
        email,
        phoneNumber
    )

    companion object {
        fun notExistWith(pubId: UUID): ResourceNotExistException =
            ResourceNotExistException(BusinessEntity::class.java, "pubId", pubId)
    }

}