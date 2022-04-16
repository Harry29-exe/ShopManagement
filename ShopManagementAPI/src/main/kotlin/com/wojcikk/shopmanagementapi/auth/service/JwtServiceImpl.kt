package com.wojcikk.shopmanagementapi.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.wojcikk.shopmanagementapi.exception.authentication.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.server.Cookie.SameSite
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import java.security.SecureRandom
import java.util.*
import javax.servlet.http.HttpServletResponse

class JwtServiceImpl(
    @Value("\${jwt-key.auth}")
    jwtAuthKey: String,
    userDetailsService: UserDetailsService
) : JwtService {

    private val authTokenAlgorithm: Algorithm = Algorithm.HMAC256(jwtAuthKey)
    private val secureRandom: SecureRandom = SecureRandom.getInstanceStrong()
    private val userDetailsService = userDetailsService

    override fun appendToken(user: Authentication, response: HttpServletResponse) {
        val now = Date()
        val expireDate = Date(now.time + EXPIRE_TIME*1000)
        val csrfToken = generateCsrfToken(CSRF_LEN)

        val token = JWT.create()
            .withSubject(user.name)
            .withClaim(CSRF_TOKEN_CLAIM_NAME, csrfToken)
            .withExpiresAt(expireDate)
            .withIssuedAt(expireDate)
            .sign(authTokenAlgorithm)

        val tokenCookie = ResponseCookie
            .from(TOKEN_COOKIE_NAME, token)
            .sameSite(SameSite.LAX.toString())
            .httpOnly(false) // todo change for production
            .secure(false) // todo change for production
            .maxAge(EXPIRE_TIME)
            .build()

        response.addHeader("Set-Cookie", tokenCookie.toString())
        response.addHeader(CSRF_TOKEN_HEADER_NAME, csrfToken)
    }

    override fun refreshToken(oldToken: String, csrfToken: String, response: HttpServletResponse) {
        val userAuth = validateToken(oldToken, csrfToken)

        appendToken(userAuth, response)
    }

    override fun validateToken(token: String, csrfToken: String): Authentication {
        val decodedToken = JWT.decode(token)
        val verifier = JWT.require(authTokenAlgorithm)
            .withClaim(CSRF_TOKEN_CLAIM_NAME, csrfToken)
            .build()

        try {
            verifier.verify(decodedToken)
        } catch (ex: JWTVerificationException) {
            throw InvalidTokenException()
        }

        val user = userDetailsService.loadUserByUsername(decodedToken.subject)
        return UsernamePasswordAuthenticationToken(
            user.username, user.password, user.authorities
        )
    }

    private fun generateCsrfToken(len: Int): String {
        val strBuilder = java.lang.StringBuilder()
        for (i in 0 until len) {
            val index = secureRandom.nextInt(CSRF_ALLOWED_CHARS.size)
            strBuilder.append(CSRF_ALLOWED_CHARS[index])
        }

        return strBuilder.toString()
    }

    companion object {
        const val TOKEN_COOKIE_NAME = "Auth-Token"
        const val EXPIRE_TIME: Long = 60*60*24*3

        const val CSRF_TOKEN_HEADER_NAME = "Csrf-Auth-Token"
        const val CSRF_TOKEN_CLAIM_NAME = "Csrf-Token"
        const val CSRF_LEN = 16
        val CSRF_ALLOWED_CHARS = arrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z',

            '3', '4',  'A', 'B', 'O', 'T', 'W', 'X', 'K',
        )
    }

}