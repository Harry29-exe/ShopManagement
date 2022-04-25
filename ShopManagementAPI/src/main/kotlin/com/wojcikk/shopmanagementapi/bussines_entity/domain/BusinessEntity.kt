package com.wojcikk.shopmanagementapi.bussines_entity.domain

import com.wojcikk.shopmanagementapi.bussines_entity.dto.BusinessEntityDTO
import com.wojcikk.shopmanagementapi.exception.resources.ResourceNotExistException
import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.utils.Wrapper
import com.wojcikk.shopmanagementapi.utils.secure.hasAnyRole
import javax.persistence.*

@Entity
@Table(name = "business_entities")
class BusinessEntity(
    @Column(name = "name")
    val entityName: String,
    val nip: String,
    val countryCode: String,
    val city: String,
    val email: String? = null,
    val phoneNumber: String? = null,
) {

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
        fun notExistWith(id: Long): ResourceNotExistException =
            ResourceNotExistException(BusinessEntity::class.java, "id", id)

        private val CREATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN, Role.SELLER)
        private val READ = Role.ALL
        private val UPDATE = arrayOf(Role.ACCOUNTANT, Role.ADMIN)
        private val DELETE = arrayOf(Role.ADMIN)

        val canCreate: Wrapper = hasAnyRole(*CREATE)

        val canRead: Wrapper = hasAnyRole(*READ)

        val canUpdate = hasAnyRole(*UPDATE)

        val canDelete = hasAnyRole(*DELETE)
    }

}