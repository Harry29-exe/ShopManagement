package com.wojcikk.shopmanagementapi.config.security

import com.wojcikk.shopmanagementapi.auth.service.JwtService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class CorsConfig(
    @Value("\${frontend-address}")
    private val frontendAddress: String
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        println(frontendAddress)
//        registry.addMapping("/login")
//            .allowCredentials(true)
//            .exposedHeaders(JwtService.CSRF_TOKEN_HEADER_NAME, "*")
//            .
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedHeaders(JwtService.CSRF_TOKEN_HEADER_NAME, "*")
            .exposedHeaders(JwtService.CSRF_TOKEN_HEADER_NAME, "*")
            .allowedMethods("*")
            .allowedOrigins(frontendAddress)
    }

}