package com.kamilwojcik.shopmanagementapi.user.service.commands

class UpdateUser(
    val username: String,
    val newName: String?,
    val newSurname: String?
)