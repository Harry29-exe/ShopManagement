package com.wojcikk.shopmanagementapi.user.domain

import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "users")
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

    @Column
    @OneToMany(mappedBy = "user")
    var roles: MutableSet<UserRole> = HashSet()
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

    fun grantRole(role: Role) {
        if (roles.indexOfFirst { r -> r.role == role } >= 0) {
            return
        }
        roles.add(UserRole(role, this))
    }

    fun revokeRole(role: Role) {
        roles.removeIf { r -> r.role == role }
    }

    fun mapToDTO(): UserDTO {
        return UserDTO(
            username, name, surname, roles.map { it.role }
        )
    }

}