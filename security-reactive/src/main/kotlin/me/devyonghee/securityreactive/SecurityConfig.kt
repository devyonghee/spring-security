package me.devyonghee.securityreactive

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        val admin = User.withUsername("admin")
            .password("{noop}password")
            .roles("ADMIN")
            .build()
        val regular = User.withUsername("regular")
            .password("{noop}password")
            .roles("REGULAR_USER")
            .build()

        return MapReactiveUserDetailsService(admin, regular)
    }

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf()
            .and()
            .authorizeExchange()
            .anyExchange()
            .access(this::authorizationDecisionMono)
            .and().httpBasic()
            .and().build()
    }

    private fun authorizationDecisionMono(
        auth: Mono<Authentication>,
        context: AuthorizationContext
    ): Mono<AuthorizationDecision> {
        val path = requestPath(context)
        if (path == "/hello") {
            return auth.isAdmin()
        }
        return Mono.just(AuthorizationDecision(false))
    }

    private fun requestPath(context: AuthorizationContext): String {
        return context.exchange.request.path.value()
    }

    private fun Mono<Authentication>.isAdmin(): Mono<AuthorizationDecision> {
        return map { auth -> auth.authorities.any { it.authority == "ROLE_ADMIN" } }
            .map { AuthorizationDecision(it) }
    }
}
