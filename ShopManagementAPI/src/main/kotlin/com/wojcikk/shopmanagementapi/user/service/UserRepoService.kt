package com.wojcikk.shopmanagementapi.user.service

import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.user.dto.UserDTO


interface UserRepoService {

    fun create(command: CreateUser): UserDTO

    fun get(username: String): UserDTO

    fun getAll(): List<UserDTO>

    fun updateDetails(command: UpdateUserName): UserDTO

    fun updatePassword(command: UpdateUserPassword)

    fun grantRole(username: String, role: Role)

    fun revokeRole(username: String, role: Role)

    fun deleteUser(username: String): Int


    class UpdateUserName(val username: String, val newName: String?, val newSurname: String?)

    class UpdateUserPassword(val username: String, val newPassword: String)

    class UpdateUserRole(val username: String, val newRole: Role)

    class CreateUser(val username: String, val name: String, val surname: String, val password: String)

}