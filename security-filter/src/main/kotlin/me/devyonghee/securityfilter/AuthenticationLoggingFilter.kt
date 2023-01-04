package me.devyonghee.securityfilter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter


class AuthenticationLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val requestId: String? = request.getHeader("Request-Id")
        logger.info("successfully authenticated request with id $requestId")
        chain.doFilter(request, response)
    }
}
