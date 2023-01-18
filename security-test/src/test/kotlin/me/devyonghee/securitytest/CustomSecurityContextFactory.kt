package me.devyonghee.securitytest

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory
import java.security.Principal

class CustomSecurityContextFactory() : WithSecurityContextFactory<WithCustomUser> {

    override fun createSecurityContext(withCustomUser: WithCustomUser): SecurityContext {
        val context: SecurityContext = SecurityContextHolder.createEmptyContext()
        context.authentication =
            UsernamePasswordAuthenticationToken(Principal { withCustomUser.username }, null, null);
        return context
    }
}
