package com.kamilwojcik.shopmanagementapi.user.service.commands

import com.kamilwojcik.shopmanagementapi.utils.validation.validate

class CreateUser(
    val username: String,
    val rawPassword: String,
    val name: String,
    val surname: String
) {
    init {
        username.validate().charsOnly().ascii()

    }
}