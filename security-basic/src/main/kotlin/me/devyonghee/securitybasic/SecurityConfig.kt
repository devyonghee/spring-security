package me.devyonghee.securitybasic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    private val successHandler: CustomAuthenticationSuccessHandler,
    private val failureHandler: CustomAuthenticationFailureHandler
) {

    @Bean
    fun userDetailService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()
        val user = User.withUsername("yong")
            .password(passwordEncoder.encode("12345"))
            .authorities("read")
            .build()
        userDetailsService.createUser(user)
        return userDetailsService
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.formLogin()
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .httpBasic()

        http.authorizeHttpRequests()
            .anyRequest().authenticated()
        return http.build()
    }

//    @Bean
//    fun initializingBean(): InitializingBean {
//        return InitializingBean {
//            SecurityContextHolder.setStrategyName(
//                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
//        }
//    }
}
