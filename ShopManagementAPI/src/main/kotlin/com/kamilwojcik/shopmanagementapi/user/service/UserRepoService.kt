package com.kamilwojcik.shopmanagementapi.user.service

import com.kamilwojcik.shopmanagementapi.user.config.security.PasswordEncoderConfig
import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import com.kamilwojcik.shopmanagementapi.user.repository.UserRepository
import com.kamilwojcik.shopmanagementapi.user.service.commands.CreateUser
import com.kamilwojcik.shopmanagementapi.user.service.commands.UpdateUser
import com.kamilwojcik.shopmanagementapi.utils.Provider
import kotlinx.coroutines.flow.Flow

interface UserRepoService {

    suspend fun get(username: String): UserDTO?

    suspend fun create(command: CreateUser): UserDTO

    suspend fun getAll(): Flow<UserDTO>

    suspend fun update(command: UpdateUser): UserDTO

    suspend fun deleteUser(username: String)

    companion object : Provider<UserRepoService> {

        private val userRepoService = UserRepoServiceImpl(
            UserRepository.provide(),
            PasswordEncoderConfig.provide()
        )

        override fun provide(): UserRepoServiceImpl {
            return userRepoService
        }

    }

}

