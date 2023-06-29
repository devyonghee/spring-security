package me.devyonghee.securityfilter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class RequestValidationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val requestId: String? = request.getHeader("Request-Id")
        if (requestId.isNullOrBlank()) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }
        chain.doFilter(request, response)
    }
}
