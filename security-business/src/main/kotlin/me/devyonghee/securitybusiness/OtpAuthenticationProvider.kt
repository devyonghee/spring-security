package me.devyonghee.securitybusiness

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class OtpAuthenticationProvider(
    private val proxy: AuthenticationServerProxy
) : AuthenticationProvider {

    override fun supports(authentication: Class<*>): Boolean {
        return OtpAuthentication::class.java.isAssignableFrom(authentication)
    }

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val code = authentication.credentials.toString()
        if (proxy.sendOTP(username, code)) {
            return OtpAuthentication(username, code)
        }
        throw BadCredentialsException("OTP is not valid")
    }
}
