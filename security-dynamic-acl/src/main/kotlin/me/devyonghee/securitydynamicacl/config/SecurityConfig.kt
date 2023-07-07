package me.devyonghee.securitydynamicacl.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun http(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic().disable()
        http.cors().disable()
        http.csrf().disable()

        http.formLogin().disable()

        http.authorizeHttpRequests()
            .anyRequest().anonymous()

        return http.build()
    }

}
