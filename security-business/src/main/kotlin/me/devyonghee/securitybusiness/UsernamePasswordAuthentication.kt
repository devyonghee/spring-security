package me.devyonghee.securitybusiness

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UsernamePasswordAuthentication(
    principal: Any,
    credentials: Any? = null,
    authorities: Collection<out GrantedAuthority> = emptyList()
) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)
