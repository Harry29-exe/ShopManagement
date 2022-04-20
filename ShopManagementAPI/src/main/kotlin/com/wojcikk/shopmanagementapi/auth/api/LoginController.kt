package com.wojcikk.shopmanagementapi.auth.api

import com.wojcikk.shopmanagementapi.auth.service.AuthenticationService
import com.wojcikk.shopmanagementapi.auth.service.JwtService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class LoginController(
    private val authenticationService: AuthenticationService,
    private val jwtService: JwtService
) : LoginApi {

    override fun login(request: LoginRequest, response: HttpServletResponse) {
        val auth = authenticationService.authenticate(request.username, request.password)
        jwtService.appendToken(auth, response)
    }

    override fun refreshToken() {
        TODO("Not yet implemented")
    }
}