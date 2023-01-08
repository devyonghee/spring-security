package me.devyonghee.securitybusiness

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class OtpAuthentication(
    principal: Any,
    credentials: Any,
    authorities: Collection<out GrantedAuthority> = emptyList()
) : UsernamePasswordAuthenticationToken(principal, credentials, authorities) {
}
