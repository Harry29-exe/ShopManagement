package com.wojcikk.shopmanagementapi.utils.secure

import com.wojcikk.shopmanagementapi.exception.authorization.NotAuthorizedException
import com.wojcikk.shopmanagementapi.user.domain.Role
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

fun getAuth(): Authentication {
    return SecurityContextHolder
        .getContext()
        .authentication
}

fun getAuthOrThrow(): Authentication {
    val auth = SecurityContextHolder
        .getContext()
        .authentication

    if (auth == null || auth.isAnonymous()) {
        throw NotAuthorizedException()
    }

    return auth
}

fun <T> usernameMatch(username: String, func: () -> T): T {
    val auth = getAuthOrThrow()

    if (auth.name == username) {
        return func()
    }

    throw NotAuthorizedException()
}

fun <T> usernameMatchOrHasRole(username: String, vararg roles: Role, func: () -> T): T {
    val auth = getAuthOrThrow()
    val rolesAsGA = roles.map(Role::toGA)
    if (auth.usernamesMatch(username) || auth.hasAnyRole(rolesAsGA)) {
        return func()
    }

    throw NotAuthorizedException()
}

fun <T> usernameMatchOrIsAdmin(username: String, func: () -> T): T {
    val auth = getAuthOrThrow()
    if (auth.name == username || auth.hasRole(Role.Admin)) {
        return func()
    }

    throw NotAuthorizedException()
}

fun <T> hasRole(vararg roles:Role, func: () -> T): T {
    if (getAuthOrThrow().hasAnyRole(roles.map(Role::toGA))) {
        return func()
    }

    throw NotAuthorizedException()
}

fun <T> isAdmin(func: () -> T): T {
    if (getAuthOrThrow().hasRole(Role.ADMIN.toGA())) {
        return func()
    }

    throw NotAuthorizedException()
}