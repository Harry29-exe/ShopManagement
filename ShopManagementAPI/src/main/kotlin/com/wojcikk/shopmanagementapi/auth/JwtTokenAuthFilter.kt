package com.wojcikk.shopmanagementapi.auth

import com.wojcikk.shopmanagementapi.auth.service.JwtService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenAuthFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val csrfHeader = request.getHeader(JwtService.CSRF_TOKEN_HEADER_NAME)
        val jwt = request.cookies?.firstOrNull { it.name == JwtService.TOKEN_COOKIE_NAME }

        if (csrfHeader == null || jwt == null) {
            filterChain.doFilter(request, response)
            return
        }

        val auth = jwtService.validateToken(jwt.value, csrfHeader)
        SecurityContextHolder.getContext().authentication = auth

        filterChain.doFilter(request, response)
    }

}