package me.devyonghee.securitydynamicacl.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun http(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }
        http.formLogin { it.disable() }

        http.authorizeHttpRequests {
            it.requestMatchers("/books/**").hasRole("ADMIN")
        }

        return http.build()
    }

}
