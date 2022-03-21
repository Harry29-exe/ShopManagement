package com.kamilwojcik.shopmanagementapi.config.exceptions

import com.kamilwojcik.shopmanagementapi.user.domain.UserEntity

class NoSuchUserException(username: String) : Exception("No user with username $username has been found") {

}

fun UserEntity?.throwIfNull(username: String): UserEntity {
    if (this == null) {
        throw NoSuchUserException(username)
    }
    return this
}