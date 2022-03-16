package com.kamilwojcik.shopmanagementapi.user.api

import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/user/")
interface UserManagementAPI {

    @GetMapping
    suspend fun getAll(): Flow<UserDTO>

    @PutMapping
    suspend fun createUser(@RequestBody requestBody: PutUserRequest): UserDTO

    @PatchMapping("{username}")
    suspend fun update(@PathVariable username: String): UserDTO

    @DeleteMapping("{username}")
    suspend fun deleteUser(@PathVariable username: String)

    companion object {

        class PutUserRequest(
            val username: String,
            val password: String,
            val name: String,
            val surname: String
        )

    }

}