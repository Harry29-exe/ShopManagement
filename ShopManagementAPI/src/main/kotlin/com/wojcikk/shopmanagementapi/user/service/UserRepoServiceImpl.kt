package com.wojcikk.shopmanagementapi.user.service

import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import com.wojcikk.shopmanagementapi.user.dto.UserDTO
import com.wojcikk.shopmanagementapi.user.repository.UserRepository
import com.wojcikk.shopmanagementapi.utils.secure.hasRole
import com.wojcikk.shopmanagementapi.utils.secure.isAdmin
import com.wojcikk.shopmanagementapi.utils.secure.usernameMatchOrHasRole
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

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

    override fun get(username: String): UserDTO = usernameMatchOrHasRole(username, Role.ADMIN) {
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

    override fun updatePassword(command: UserRepoService.UpdateUserPassword) {

    }

    override fun updateRole(command: UserRepoService.UpdateUserRole) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String): Int {
        return userRepo.deleteByUsername(username)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo
            .findByUsername(username)
            ?: throw NoSuchElementException(username)

        return User(
            user.username,
            user.passwordHash,
            listOf(
                user.role.toGA()
            )
        )
    }

}