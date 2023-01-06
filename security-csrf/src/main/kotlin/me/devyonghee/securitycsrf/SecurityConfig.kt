package me.devyonghee.securitycsrf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun configure(
        http: HttpSecurity,
        customCsrfTokenRepository: CustomCsrfTokenRepository
    ): SecurityFilterChain {
        return http.csrf {
            it.csrfTokenRepository(customCsrfTokenRepository)
            it.ignoringRequestMatchers("/ciao")
        }.authorizeHttpRequests()
            .anyRequest().permitAll()
            .and()
            .build()
    }
}
