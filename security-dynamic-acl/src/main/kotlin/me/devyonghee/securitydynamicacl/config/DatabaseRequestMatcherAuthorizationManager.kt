package me.devyonghee.securitydynamicacl.config

import jakarta.servlet.http.HttpServletRequest
import java.util.function.Supplier
import me.devyonghee.securitydynamicacl.urlendpoint.HttpMethod
import me.devyonghee.securitydynamicacl.urlendpoint.UserRole
import me.devyonghee.securitydynamicacl.urlendpoint.UserRoleUrlEndpoint
import me.devyonghee.securitydynamicacl.urlendpoint.UserRoleUrlEndpointService
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcherEntry
import org.springframework.stereotype.Component

@Component
class DatabaseRequestMatcherAuthorizationManager(
    private val userRoleUrlEndpointService: UserRoleUrlEndpointService,
) : AuthorizationManager<HttpServletRequest> {

    private lateinit var mappings: List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>>

    init {
        reload()
    }

    fun reload() {
        mappings = userRoleUrlEndpointService.all()
            .groupBy {
                when (it.httpMethod) {
                    HttpMethod.ALL -> AntPathRequestMatcher(it.urlPattern)
                    else -> AntPathRequestMatcher(it.urlPattern, it.httpMethod.name)
                }
            }.map { (matcher, endpoints) -> RequestMatcherEntry(matcher, authorizationManager(endpoints)) }
    }

    private fun authorizationManager(endpoints: Collection<UserRoleUrlEndpoint>): AuthorizationManager<RequestAuthorizationContext> {
        if (endpoints.any { it.role == UserRole.Role.ANONYMOUS }) {
            return AuthorizationManager { _, _ -> AuthorizationDecision(true) }
        }
        return AuthorityAuthorizationManager.hasAnyRole(*endpoints.map { it.role.name }.toTypedArray())
    }

    override fun check(
        authentication: Supplier<Authentication>,
        request: HttpServletRequest,
    ): AuthorizationDecision {
        return mappings.map { it.entry to it.requestMatcher.matcher(request) }
            .firstOrNull { it.second.isMatch }
            ?.let {
                it.first.check(
                    authentication,
                    RequestAuthorizationContext(request, it.second.variables)
                )
            } ?: DENY
    }

    companion object {
        private val DENY: AuthorizationDecision = AuthorizationDecision(false)
    }
}