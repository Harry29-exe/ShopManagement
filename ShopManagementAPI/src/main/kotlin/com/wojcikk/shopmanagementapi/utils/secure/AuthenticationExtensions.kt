package com.wojcikk.shopmanagementapi.utils.secure

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

fun Authentication.isAnonymous(): Boolean {
    return this is AnonymousAuthenticationToken
}

fun Authentication.hasAnyRole(roles: Iterable<GrantedAuthority>): Boolean {
    for (role in authorities) {
        if (roles.contains(role)) {
            return true
        }
    }

    return false
}

fun Authentication.hasRole(role: GrantedAuthority): Boolean {
    return authorities.contains(role)
}

fun Authentication.usernamesMatch(username: String): Boolean {
    return name == username
}

fun Authentication.hasAllRoles(roles: Iterable<GrantedAuthority>): Boolean {
    for (role in roles) {
        if (!authorities.contains(role)) {
            return false
        }
    }

    return true
}