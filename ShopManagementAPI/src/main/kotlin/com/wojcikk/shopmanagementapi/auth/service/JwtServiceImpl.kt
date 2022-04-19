package com.wojcikk.shopmanagementapi.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.CSRF_ALLOWED_CHARS
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.CSRF_LEN
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.CSRF_TOKEN_CLAIM_NAME
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.CSRF_TOKEN_HEADER_NAME
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.EXPIRE_TIME
import com.wojcikk.shopmanagementapi.auth.service.JwtService.Companion.TOKEN_COOKIE_NAME
import com.wojcikk.shopmanagementapi.exception.authentication.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.server.Cookie.SameSite
import org.springframework.http.ResponseCookie
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*
import javax.servlet.http.HttpServletResponse

@Service
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
            .path("/")
            .sameSite(SameSite.NONE.toString())
            .httpOnly(true)
            .secure(true) // todo change for production
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

}