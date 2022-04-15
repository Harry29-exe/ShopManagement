package com.wojcikk.shopmanagementapi.utils.secure

import com.wojcikk.shopmanagementapi.exception.authorization.NotAuthorizedException
import com.wojcikk.shopmanagementapi.user.domain.Role
import com.wojcikk.shopmanagementapi.utils.SimpleFun
import com.wojcikk.shopmanagementapi.utils.Wrapper

fun usernameMatchOrHasRole(username: String, vararg roles: Role): Wrapper
= object : Wrapper { override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>
{
    val auth = getAuthOrThrow()
    val rolesAsGa = roles.map(Role::toGA)

    if (auth.usernamesMatch(username) || auth.hasAnyRole(rolesAsGa)) {
        return f
    }

    throw NotAuthorizedException()
} }

fun usernameMatchOrIsAdmin(username: String): Wrapper
= object : Wrapper { override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>
{
    val auth = getAuthOrThrow()

    if (auth.name == username || auth.hasRole(Role.Admin)) {
        return f
    }

    throw NotAuthorizedException()
} }

fun hasAnyRole(vararg roles: Role): Wrapper
= object : Wrapper { override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>
{
    val auth = getAuthOrThrow()
    val rolesAsGA = roles.map { it.toGA() }
    if (auth.hasAnyRole(rolesAsGA)) {
        return f
    }
    throw NotAuthorizedException()
} }


