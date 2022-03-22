package com.wojcikk.shopmanagementapi.user.repository

import com.wojcikk.shopmanagementapi.user.domain.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByUsername(username: String): UserEntity?

    fun deleteByUsername(username: String): Int

}