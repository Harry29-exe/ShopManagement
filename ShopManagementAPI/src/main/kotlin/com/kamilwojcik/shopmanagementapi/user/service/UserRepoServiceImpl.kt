package com.kamilwojcik.shopmanagementapi.user.service

import com.kamilwojcik.shopmanagementapi.user.config.db.DBConnectionConfig
import com.kamilwojcik.shopmanagementapi.user.domain.UserEntity
import com.kamilwojcik.shopmanagementapi.user.dto.UserDTO
import com.kamilwojcik.shopmanagementapi.user.repository.UserRepository
import com.kamilwojcik.shopmanagementapi.user.repository.UserRepositoryImpl
import com.kamilwojcik.shopmanagementapi.user.service.commands.CreateUser
import com.kamilwojcik.shopmanagementapi.user.service.commands.UpdateUser
import com.kamilwojcik.shopmanagementapi.utils.Provider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRepoServiceImpl(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserRepoService {

    override suspend fun create(command: CreateUser): UserDTO {
        val entity = UserEntity(
            command.username,
            passwordEncoder.encode(command.rawPassword),
            command.name,
            command.surname
        )

        return userRepo.save(entity)
            .mapToDTO()
    }

    override suspend fun get(username: String): UserDTO? {
        return userRepo.findByUsername(username)?.mapToDTO()
    }

    override suspend fun getAll(): Flow<UserDTO> {
        return userRepo
            .findAll()
            .map(UserEntity::mapToDTO)
    }

    override suspend fun update(command: UpdateUser): UserDTO {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(username: String) {
        TODO("Not yet implemented")
    }


}