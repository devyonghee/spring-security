package me.devyonghee.securityfilter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig(
    private val filter: StaticKeyAuthenticationFilter
) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain? {
        http.addFilterBefore(RequestValidationFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAfter(AuthenticationLoggingFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAt(filter, BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests()
            .anyRequest().permitAll()
        return http.build()
    }
}
