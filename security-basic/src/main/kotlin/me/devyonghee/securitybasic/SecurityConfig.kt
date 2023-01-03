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
        val user1 = User.withUsername("yong")
            .password(passwordEncoder.encode("12345"))
            // .authorities(Authority.READ)    // access("hasAuthority('READ') and !hasAuthority('DELETE')")
            .roles("ADMIN")
            .build()

        val user2 = User.withUsername("jane")
            .password(passwordEncoder.encode("12345"))
            .roles("MANAGER")
            .build()
        userDetailsService.createUser(user1)
        userDetailsService.createUser(user2)
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
//            .anyRequest().hasAuthority(Authority.READ.name)
            .requestMatchers("/hello").hasRole("ADMIN")
            .requestMatchers("/ciao").hasRole("MANAGER")
            .requestMatchers("/product/{code:^[0-9]*$}").permitAll() // /{param:regex}
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
