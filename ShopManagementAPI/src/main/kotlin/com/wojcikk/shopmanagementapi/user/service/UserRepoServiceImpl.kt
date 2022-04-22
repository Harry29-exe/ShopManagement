package com.wojcikk.shopmanagementapi.user.service

import com.wojcikk.shopmanagementapi.exception.user.NoSuchUserException
import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import com.wojcikk.shopmanagementapi.user.repository.UserRepository
import com.wojcikk.shopmanagementapi.utils.secure.isAdmin
import com.wojcikk.shopmanagementapi.utils.secure.usernameMatchOrHasRole
import com.wojcikk.shopmanagementapi.utils.secure.usernameMatchOrIsAdmin
import com.wojcikk.shopmanagementapi.utils.wrap
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserRepoServiceImpl(
    val userRepo: UserRepository,
    val passwordEncoder: PasswordEncoder

) : UserRepoService, UserDetailsService {

    override fun create(command: UserRepoService.CreateUser): UserDTO = isAdmin {
        userRepo
            .save(
                UserEntity(
                    command.username,
                    command.name,
                    command.surname,
                    passwordEncoder,
                    command.password
                )
            )
            .mapToDTO()
    }

    override fun get(username: String): UserDTO = usernameMatchOrHasRole(username, Role.ADMIN)
    {
        userRepo
            .findByUsername(username)
            ?.mapToDTO()
            ?: throw NoSuchElementException(username)
    }

    override fun getAll(): List<UserDTO> = isAdmin {
        userRepo
            .findAll()
            .map { it.mapToDTO() }
    }

    override fun updateDetails(command: UserRepoService.UpdateUserName): UserDTO = isAdmin {
        userRepo
            .findByUsername(command.username)
            ?.updateName(command.newName, command.newSurname)
            ?.mapToDTO()
            ?: throw NoSuchElementException(command.username)
    }

    override fun updatePassword(command: UserRepoService.UpdateUserPassword) =
        usernameMatchOrIsAdmin(command.username, fun() {
            userRepo
                .findByUsername(command.username)
                ?.updatePassword(command.newPassword, passwordEncoder)
                ?: throw NoSuchUserException(command.username)
        })

    override fun grantRole(username: String, role: Role) = wrap(
        isAdmin
    ) {
        userRepo
            .findByUsername(username)
            ?.grantRole(role)
            ?: throw NoSuchUserException(username)
    }

    override fun revokeRole(username: String, role: Role) = wrap(
        isAdmin
    ) {
        userRepo
            .findByUsername(username)
            ?.revokeRole(role)
            ?: throw NoSuchUserException(username)
    }

    override fun deleteUser(username: String): Int = isAdmin {
        userRepo.deleteByUsername(username)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo
            .findByUsername(username)
            ?: throw NoSuchUserException(username)

        return User(
            user.username,
            user.passwordHash,
            user.roles.map { it.role.toGA() }
        )
    }

}