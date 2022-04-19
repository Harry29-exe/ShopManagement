package com.wojcikk.shopmanagementapi.auth.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@RequestMapping("/login")
@CrossOrigin(origins = ["http://localhost:3000"], allowCredentials = "true")
interface LoginApi {

    @PostMapping
    @CrossOrigin(origins = ["http://localhost:3000"], allowedHeaders = ["Csrf-Auth-Token"])
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse)

    @PostMapping("/refresh")
    fun refreshToken()

}

class LoginRequest(
    val username: String,
    val password: String,
    val dontLogout: Boolean
)