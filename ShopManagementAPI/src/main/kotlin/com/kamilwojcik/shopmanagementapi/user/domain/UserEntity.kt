package com.kamilwojcik.shopmanagementapi.user.domain

import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import com.kamilwojcik.shopmanagementapi.utils.repository.EntityInfo
import org.springframework.data.relational.core.mapping.Table

@Table("users")
class UserEntity(
    private val id: Int,
    private val username: String,
    private val passwordHash: String,
    private var name: String,
    private var surname: String
) {


    fun mapToDTO(): UserDTO {
        return UserDTO(username, name, surname)
    }

    object Info : EntityInfo<UserEntity> {

        override val clazz: Class<UserEntity> = UserEntity::class.java

        override val ID: String = "id"
        val USERNAME = "username"
        val PASSWORD_HASH = "password_hash"
        val NAME = "name"
        val SURNAME = "surname"

    }

}