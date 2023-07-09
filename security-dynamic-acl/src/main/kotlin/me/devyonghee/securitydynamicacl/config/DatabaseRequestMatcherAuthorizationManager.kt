package me.devyonghee.securitydynamicacl.config

import jakarta.servlet.http.HttpServletRequest
import java.util.function.Supplier
import me.devyonghee.securitydynamicacl.account.UserRole
import me.devyonghee.securitydynamicacl.urlendpoint.HttpMethod
import me.devyonghee.securitydynamicacl.urlendpoint.UserRoleUrlEndpointService
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
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

    final fun reload() {
        mappings = userRoleUrlEndpointService.all()
            .groupBy {
                when (it.httpMethod) {
                    HttpMethod.ALL -> AntPathRequestMatcher(it.urlPattern)
                    else -> AntPathRequestMatcher(it.urlPattern, it.httpMethod.name)
                }
            }.map { (matcher, endpoints) ->
                RequestMatcherEntry(
                    matcher,
                    AuthorityAuthorizationManager.hasAnyRole<RequestAuthorizationContext>(
                        *endpoints.map { it.role.name }.toTypedArray()
                    ).apply {
                        setRoleHierarchy(ROLE_HIERARCHY)
                    }
                )
            }
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
        private val ROLE_HIERARCHY: RoleHierarchy = LowestBaseRoleHierarchy("ROLE_${UserRole.Role.ANONYMOUS}")
    }
}