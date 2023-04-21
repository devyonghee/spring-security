package me.devyonghee.securityentrypoint

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.ExceptionTranslationFilter

@Configuration
class SecurityConfig(
    private val requestIdValidationFilter: RequestIdValidationFilter
) {

    @Bean
    fun http(http: HttpSecurity): SecurityFilterChain {
        val build = http
            .httpBasic().disable()
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()

            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/hello").hasAuthority(
                RequestIdValidationFilter.REQUEST_HEADER_AUTHORITY.authority
            )
            .anyRequest().permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint { _, response, authException ->
                response.sendError(HttpStatus.UNAUTHORIZED.value())
                response.writer.write("body : ${authException.message}")
            }
            .accessDeniedHandler() { _, response, accessDeniedException ->
                response.sendError(HttpStatus.FORBIDDEN.value())
                response.writer.write("body : ${accessDeniedException.message}")
            }
            .and()

            .addFilterAfter(requestIdValidationFilter, ExceptionTranslationFilter::class.java)
            .build()
        return build
    }
}