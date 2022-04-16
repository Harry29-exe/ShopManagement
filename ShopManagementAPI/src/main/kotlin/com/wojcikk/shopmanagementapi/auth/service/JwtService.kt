package com.wojcikk.shopmanagementapi.auth.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import javax.servlet.http.HttpServletResponse

interface JwtService {

    fun appendToken(user: Authentication, response: HttpServletResponse)

    fun refreshToken(oldToken: String, csrfToken: String, response: HttpServletResponse)

    fun validateToken(token: String, csrfToken: String): Authentication

}