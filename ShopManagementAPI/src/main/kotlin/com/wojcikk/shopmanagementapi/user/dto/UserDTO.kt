package com.wojcikk.shopmanagementapi.user.dto

import com.wojcikk.shopmanagementapi.user.domain.Role

data class UserDTO(
    val username: String,
    val name: String,
    val surname: String,
    val roles: List<Role>
)
