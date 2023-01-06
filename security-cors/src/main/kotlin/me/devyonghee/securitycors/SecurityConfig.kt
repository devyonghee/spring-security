package me.devyonghee.securitycors

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
class SecurityConfig {

    @Bean
    fun configure(
        http: HttpSecurity
    ): SecurityFilterChain {
        return http.cors { c ->
            val source = CorsConfigurationSource {
                val configuration = CorsConfiguration()
                configuration.addAllowedOrigin("example.com")
                configuration.addAllowedOrigin("example.org")
                configuration.addAllowedMethod("*")
                configuration
            }
            c.configurationSource(source)
        }.csrf()
            .disable()
            .authorizeHttpRequests()
            .anyRequest().permitAll()
            .and()
            .build()
    }
}
