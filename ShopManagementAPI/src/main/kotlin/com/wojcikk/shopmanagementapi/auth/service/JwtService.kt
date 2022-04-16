package com.wojcikk.shopmanagementapi.auth.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import javax.servlet.http.HttpServletResponse

interface JwtService {

    fun addToken(user: User, response: HttpServletResponse)

    fun refreshToken(token: String, response: HttpServletResponse)

    fun validateToken(token: String): Authentication

}