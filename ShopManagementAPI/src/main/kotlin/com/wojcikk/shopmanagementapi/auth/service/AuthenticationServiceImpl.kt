package com.wojcikk.shopmanagementapi.auth.service

import com.wojcikk.shopmanagementapi.exception.authentication.BadCredentialsException
import com.wojcikk.shopmanagementapi.exception.user.NoSuchUserException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override fun authenticate(username: String, password: String): Authentication {
        var user = userDetailsService
            .loadUserByUsername(username)
            ?: throw NoSuchUserException(username)

        if (passwordEncoder.matches(password, user.password)) {
            val userAuth = UsernamePasswordAuthenticationToken(
                username, user.password, user.authorities
            )

            SecurityContextHolder
                .getContext()
                .authentication = userAuth

            return userAuth
        }

        throw BadCredentialsException()
    }

}