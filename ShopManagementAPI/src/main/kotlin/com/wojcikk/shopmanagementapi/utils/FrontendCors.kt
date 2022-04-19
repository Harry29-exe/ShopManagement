package com.wojcikk.shopmanagementapi.utils

import com.wojcikk.shopmanagementapi.auth.service.JwtService
import com.wojcikk.shopmanagementapi.auth.service.JwtServiceImpl
import org.springframework.web.bind.annotation.CrossOrigin
import java.lang.annotation.Documented

@CrossOrigin(origins = ["http://localhost:3000"],
    allowedHeaders = [JwtService.CSRF_TOKEN_HEADER_NAME],
    allowCredentials = "true",
)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class FrontendCors()
