package com.kamilwojcik.shopmanagementapi.user.repository

import com.kamilwojcik.shopmanagementapi.user.config.db.DBConnectionConfig
import com.kamilwojcik.shopmanagementapi.user.domain.UserEntity
import com.kamilwojcik.shopmanagementapi.utils.Provider
import com.kamilwojcik.shopmanagementapi.utils.repository.AppCoroutineCrudRepository

interface UserRepository : AppCoroutineCrudRepository<UserEntity, Int> {

    suspend fun findByUsername(username: String): UserEntity?



    companion object : Provider<UserRepository> {

        private val userRepository = UserRepositoryImpl(DBConnectionConfig.template)

        override fun provide(): UserRepository {
            return userRepository
        }

    }

}