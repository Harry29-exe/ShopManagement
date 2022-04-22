package com.wojcikk.shopmanagementapi.auth.service

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletResponse

interface JwtService {

    fun appendToken(user: Authentication, response: HttpServletResponse)

    fun refreshToken(oldToken: String, csrfToken: String, response: HttpServletResponse)

    fun validateToken(token: String, csrfToken: String): Authentication

    companion object {
        const val TOKEN_COOKIE_NAME = "Authentication"
        const val EXPIRE_TIME: Long = 60 * 60 * 24 * 3

        const val CSRF_TOKEN_HEADER_NAME = "Csrf-Auth-Token"
        const val CSRF_TOKEN_CLAIM_NAME = "Csrf-Token"
        const val CSRF_LEN = 16
        val CSRF_ALLOWED_CHARS = arrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',

            '3', '4', 'A', 'B', 'O', 'T', 'W', 'X', 'K',
        )
    }

}