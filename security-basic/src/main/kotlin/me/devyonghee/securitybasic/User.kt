package me.devyonghee.securitybasic

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(
    private val name: String,
    private val password: String
) : UserDetails {

    override fun getUsername(): String {
        return name
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): Collection<out GrantedAuthority> {
        return listOf(Authority.READ)
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }
}
