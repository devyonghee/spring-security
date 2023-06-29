package me.devyonghee.globalmethod

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val evaluator: PermissionEvaluator
) : GlobalAuthenticationConfigurerAdapter() {

    override fun init(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("readUser").password("{noop}password").authorities("read")
            .and()
            .withUser("writeUser").password("{noop}password").authorities("write")
            .and()
            .withUser("admin").password("{noop}password").roles("admin")
            .and()
            .withUser("manager").password("{noop}password").roles("manager")
    }

    @Bean
    fun methodSecurityExpressionHandler(): MethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setPermissionEvaluator(evaluator)
        return expressionHandler
    }
}
