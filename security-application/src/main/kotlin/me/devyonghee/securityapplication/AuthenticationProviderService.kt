package me.devyonghee.securityapplication

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

class AuthenticationProviderService(
    private val jpaUserDetailsService: JpaUserDetailsService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val sCryptPasswordEncoder: SCryptPasswordEncoder
) : AuthenticationProvider {

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name;
        val password = authentication.credentials.toString()

        val user = jpaUserDetailsService.loadUserByUsername(username)

        return when (user.user.encryptionAlgorithm) {
            EncryptionAlgorithm.BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder)
            EncryptionAlgorithm.SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder)
        }
    }

    private fun checkPassword(
        user: CustomUserDetails, password: String, passwordEncoder: PasswordEncoder
    ): Authentication {
        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("bad credentials")
        }
        return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
    }
}
