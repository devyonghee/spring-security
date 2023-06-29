package me.devyonghee.securityoauth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.oauth2Login() {
            it.clientRegistrationRepository(clientRepository())
        }
            .authorizeHttpRequests()
            .anyRequest().authenticated()
            .and()
            .build()
    }

    fun clientRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(clientRegistration())
    }

    fun clientRegistration(): ClientRegistration {
        return CommonOAuth2Provider.GITHUB
            .getBuilder("github")
            .clientId("client-id")
            .clientSecret("client-secret")
            .build()
    }
}
