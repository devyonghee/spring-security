package me.devyonghee.securityapplication.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun sCryptPasswordEncoder(): SCryptPasswordEncoder {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.formLogin()
            .defaultSuccessUrl("/main", true)
            .and()
            .authorizeHttpRequests()
            .anyRequest().hasAuthority("read")
            .and()
            .build()
    }
}
