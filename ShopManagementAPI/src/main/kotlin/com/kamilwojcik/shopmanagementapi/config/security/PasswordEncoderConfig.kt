package com.kamilwojcik.shopmanagementapi.config.security

import com.kamilwojcik.shopmanagementapi.utils.Provider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

object PasswordEncoderConfig : Provider<PasswordEncoder>{

    private val passwordEncoder = BCryptPasswordEncoder(10)

    override fun provide(): PasswordEncoder {
        return passwordEncoder;
    }


}