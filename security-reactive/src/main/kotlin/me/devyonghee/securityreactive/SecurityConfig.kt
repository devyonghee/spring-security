package me.devyonghee.securityreactive

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User

@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        val readUser = User.withUsername("readUser")
            .password("{noop}password")
            .authorities("read")
            .build()
        return MapReactiveUserDetailsService(readUser)
    }
}
