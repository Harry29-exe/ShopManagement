package com.kamilwojcik.shopmanagementapi.user.repository

import com.kamilwojcik.shopmanagementapi.user.domain.UserEntity
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UserRepositoryImplTest {

    private val userRepository = UserRepository.provide()

    @BeforeEach
    fun initDB(): Unit = runBlocking {
        userRepository.save(UserEntity("bob", "passw", "bob", "smith"))
        userRepository.save(UserEntity("bob2", "passw", "bob", "smith"))
    }

    @Test
    fun should_return_user_by_username() = runBlocking {
        val entity = userRepository.findByUsername("bob")

        assert(entity != null)
    }

}