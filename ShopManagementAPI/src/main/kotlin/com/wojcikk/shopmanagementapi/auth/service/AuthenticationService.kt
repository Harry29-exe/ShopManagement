package com.wojcikk.shopmanagementapi.auth.service

import org.springframework.security.core.Authentication

interface AuthenticationService {

    fun authenticate(username: String, password: String): Authentication

}