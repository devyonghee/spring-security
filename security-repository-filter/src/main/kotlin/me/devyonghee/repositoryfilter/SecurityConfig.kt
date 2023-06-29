package me.devyonghee.repositoryfilter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig : GlobalAuthenticationConfigurerAdapter() {

    override fun init(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("readUser").password("{noop}password").authorities("read")
            .and()
            .withUser("writeUser").password("{noop}password").authorities("write")
    }

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension {
        return SecurityEvaluationContextExtension()
    }
}
