package me.devyonghee.securitycsrf

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomCsrfTokenRepository(
    private val jpaTokenRepository: JpaTokenRepository
) : CsrfTokenRepository {

    override fun generateToken(request: HttpServletRequest): CsrfToken {
        return DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", UUID.randomUUID().toString())
    }

    override fun saveToken(
        token: CsrfToken,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val identifier: String = request.getHeader("X-IDENTIFIER")

        jpaTokenRepository.findByIdentifier(identifier)?.let {
            it.token = token.token
        } ?: run {
            jpaTokenRepository.save(Token(identifier, token.token))
        }
    }

    override fun loadToken(request: HttpServletRequest): CsrfToken? {
        val identifier: String = request.getHeader("X-IDENTIFIER")
        return jpaTokenRepository.findByIdentifier(identifier)?.let { token ->
            DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.token)
        }
    }
}
