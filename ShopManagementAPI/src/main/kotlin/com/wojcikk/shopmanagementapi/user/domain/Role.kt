package com.wojcikk.shopmanagementapi.user.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role {

    ADMIN,
    SELLER,
    USER;

    fun toGA(): GrantedAuthority {
        return SimpleGrantedAuthority(this.name)
    }

}