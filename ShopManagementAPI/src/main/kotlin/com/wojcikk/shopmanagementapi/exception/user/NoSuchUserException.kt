package com.wojcikk.shopmanagementapi.exception.user

class NoSuchUserException : Exception {

    constructor(username: String) : super("User with username $username does not exist")
}