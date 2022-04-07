package com.wojcikk.shopmanagementapi.user.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role {

    ADMIN,
    SELLER,
    ACCOUNTANT,
    USER;

    fun toGA(): GrantedAuthority {
        return SimpleGrantedAuthority(this.name)
    }


    companion object {
        val Admin: GrantedAuthority = ADMIN.toGA()
        val Seller: GrantedAuthority = SELLER.toGA()
        val User: GrantedAuthority = USER.toGA()

        val ALL = arrayOf(ADMIN, SELLER, ACCOUNTANT, USER)
    }

}