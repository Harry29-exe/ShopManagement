package com.wojcikk.shopmanagementapi.auth.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@RequestMapping("/login")
interface LoginApi {

    @PostMapping
    fun login(@RequestBody request: LoginRequest, response: HttpServletResponse)

    @PostMapping("/refresh")
    fun refreshToken()

}

class LoginRequest(
    val username: String,
    val password: String,
    val dontLogout: Boolean
)