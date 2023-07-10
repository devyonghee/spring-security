package me.devyonghee.securitydynamicacl.config

import me.devyonghee.securitydynamicacl.urlendpoint.UserRole
import me.devyonghee.securitydynamicacl.urlendpoint.UserRoleUrlEndpointService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

@Configuration
class SecurityConfig(
    private val userRoleUrlEndpointService: UserRoleUrlEndpointService,
) {
    @Bean
    fun http(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }

        http.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }

        http.addFilterBefore(databaseAuthorizationFilter(), AuthorizationFilter::class.java)
        return http.build()
    }

    fun databaseAuthorizationFilter(): AuthorizationFilter {
        return AuthorizationFilter(DatabaseRequestMatcherAuthorizationManager(userRoleUrlEndpointService))
    }

    @Bean
    fun userDetailService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()
        val user1 = User.withUsername("yong")
            .password(passwordEncoder.encode("12345"))
            .roles(UserRole.Role.ADMIN.name)
            .build()

        val user2 = User.withUsername("client")
            .password(passwordEncoder.encode("12345"))
            .roles(UserRole.Role.CLIENT.name)
            .build()
        userDetailsService.createUser(user1)
        userDetailsService.createUser(user2)
        return userDetailsService
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
