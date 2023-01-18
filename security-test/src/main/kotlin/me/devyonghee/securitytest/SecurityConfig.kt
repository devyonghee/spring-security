package me.devyonghee.securitytest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val john = User.withUsername("john")
            .password("{noop}password")
            .authorities("read")
            .build()
        return InMemoryUserDetailsManager(john)
    }
}
