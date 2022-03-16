package com.kamilwojcik.shopmanagementapi.user.api

import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import com.kamilwojcik.shopmanagementapi.user.service.UserRepoService
import com.kamilwojcik.shopmanagementapi.user.service.UserRepoServiceImpl
import com.kamilwojcik.shopmanagementapi.user.service.commands.CreateUser
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.RestController

@RestController
class UserManagementAPIImpl : UserManagementAPI {
    private val userService: UserRepoService = UserRepoService.provide()

    override suspend fun getAll(): Flow<UserDTO> {
        return userService.getAll()
    }

    override suspend fun createUser(requestBody: UserManagementAPI.Companion.PutUserRequest): UserDTO {
        return userService.create(
            CreateUser(
                requestBody.username,
                requestBody.password,
                requestBody.name,
                requestBody.surname
            )
        );
    }

    override suspend fun update(username: String): UserDTO {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(username: String) {
        TODO("Not yet implemented")
    }

}