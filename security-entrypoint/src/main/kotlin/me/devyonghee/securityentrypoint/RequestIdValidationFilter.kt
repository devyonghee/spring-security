package me.devyonghee.securityentrypoint

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestIdValidationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val requestId: String? = request.getHeader("Request-Id")
        if (requestId.isNullOrBlank()) {
            chain.doFilter(request, response)
            return
        }
        if (requestId == REQUEST_HEADER_AUTHORITY.authority) {
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(requestId, requestId, listOf(REQUEST_HEADER_AUTHORITY))
            chain.doFilter(request, response)
            return
        }

        throw DisabledException("not allowed")
    }

    companion object {
        val REQUEST_HEADER_AUTHORITY: GrantedAuthority =
            GrantedAuthority { "REQUEST_AUTHORITY" }

        val ANY_AUTHORITY: GrantedAuthority =
            GrantedAuthority { "ANY_AUTHORITY" }
    }
}
