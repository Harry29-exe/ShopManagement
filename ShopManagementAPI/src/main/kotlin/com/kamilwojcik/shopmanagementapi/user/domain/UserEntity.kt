package com.kamilwojcik.shopmanagementapi.user.domain

import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import com.kamilwojcik.shopmanagementapi.utils.repository.EntityInfo
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.crypto.password.PasswordEncoder

@Table("users")
class UserEntity(
    val username: String,
    passwordHash: String,
    name: String,
    surname: String
) {
    @Id
    var id: Int = 0

    var passwordHash: String = passwordHash
        private set
    var name = name
        private set
    var surname = surname
        private set

    fun updateName(name: String?, surname: String?) {
        if (name != null) this.name = name
        if (surname != null) this.surname = surname
    }

    fun updatePassword(newPassword: String, encoder: PasswordEncoder) {
        passwordHash = encoder.encode(newPassword)
    }

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