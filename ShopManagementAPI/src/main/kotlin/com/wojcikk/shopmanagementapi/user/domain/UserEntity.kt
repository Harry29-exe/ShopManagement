package com.wojcikk.shopmanagementapi.user.domain

import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    username: String,
    name: String,
    surname: String,
    encoder: PasswordEncoder,
    password: String
) {

    @Id
    val id: Long = 0

    @Column(unique = true, nullable = false)
    val username = username

    @Column(unique = true, nullable = false)
    var role: Role = Role.USER
        private set

    @Column(nullable = false)
    var name = name
        private set

    @Column(nullable = false)
    var surname = surname
        private set

    @Column(nullable = false)
    var passwordHash: String = encoder.encode(password)
        private set


    fun updateName(name: String? = null, surname: String? = null): UserEntity {
        if (name != null)
            this.name = name
        if (surname != null)
            this.surname = surname

        return this
    }

    fun updatePassword(password: String, encoder: PasswordEncoder): UserEntity {
        passwordHash = encoder.encode(password)

        return this
    }

    fun changeRole(role: Role) {
        this.role = role
    }

    fun mapToDTO(): UserDTO {
        return UserDTO(
            username, name, surname
        )
    }

}