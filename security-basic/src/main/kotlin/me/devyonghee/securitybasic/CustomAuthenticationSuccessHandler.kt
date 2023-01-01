package me.devyonghee.securitybasic

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val authorities = authentication.authorities
        val auth = authorities.firstOrNull {
            it.authority.equals("read")
        }
        auth?.let {
            response.sendRedirect("/home")
        } ?: run {
            response.sendRedirect("/error")
        }
    }
}
