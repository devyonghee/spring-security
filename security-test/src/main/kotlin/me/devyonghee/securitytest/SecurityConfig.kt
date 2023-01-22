package me.devyonghee.securitytest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val successHandler: CustomAuthenticationSuccessHandler
) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val readUser = User.withUsername("readUser")
            .password("{noop}password")
            .authorities("read")
            .build()
        val writeUser = User.withUsername("writeUser")
            .password("{noop}password")
            .authorities("write")
            .build()
        return InMemoryUserDetailsManager(readUser, writeUser)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf().and()
            .cors { customizer ->
                customizer.configurationSource {
                    val configuration = CorsConfiguration()
                    configuration.addAllowedOrigin("*")
                    configuration.addAllowedMethod("*")
                    configuration
                }
            }
            .formLogin()
            .successHandler(successHandler)
            .and()
            .httpBasic()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/hello").permitAll()
            .requestMatchers("/test").permitAll()
            .anyRequest().authenticated()
            .and()
            .build()
    }
}
