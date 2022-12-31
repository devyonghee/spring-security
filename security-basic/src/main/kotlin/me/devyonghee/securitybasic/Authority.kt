package me.devyonghee.securitybasic

import org.springframework.security.core.GrantedAuthority

enum class Authority: GrantedAuthority {
    READ, WRITE;

    override fun getAuthority(): String {
        return name
    }
}
