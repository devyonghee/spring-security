package me.devyonghee.securityfilter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class StaticKeyAuthenticationFilter(
    @Value("\${authorization.key}")
    private val authorizationKey: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val authentication = request.getHeader("Authorization")
        if (authorizationKey != authentication) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }
        chain.doFilter(request, response)
    }
}
