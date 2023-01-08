package me.devyonghee.securitybusiness

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.crypto.SecretKey

@Component
class InitialAuthenticationFilter(
    private val manager: AuthenticationManager,
    @Value("\${jwt.signing.key}")
    private val signingKey: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val username = request.getHeader("username")
        val password = request.getHeader("password")

        request.getHeader("code")?.let { code ->
            val authenticate = manager.authenticate(OtpAuthentication(username, code))
            Keys.hmacShaKeyFor(signingKey.toByteArray()).also { key ->
                response.addHeader("Authorization", jwt(authenticate, key))
            }
        } ?: run {
            manager.authenticate(UsernamePasswordAuthentication(username, password))
        }
    }

    private fun jwt(
        authenticate: Authentication,
        key: SecretKey
    ): String {
        return Jwts.builder()
            .setClaims(mapOf("username" to authenticate.name))
            .signWith(key)
            .compact()
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath != "/login"
    }
}
