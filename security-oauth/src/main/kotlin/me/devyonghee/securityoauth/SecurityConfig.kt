package me.devyonghee.securityoauth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.oauth2Login()
            .and()
            .authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .build()
    }
}
