package me.devyonghee.securitydynamicacl.config

import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class LowestBaseRoleHierarchy(role: String) : RoleHierarchy {

    private val defaultRoles: Collection<SimpleGrantedAuthority> = setOf(SimpleGrantedAuthority(role))

    override fun getReachableGrantedAuthorities(authorities: Collection<GrantedAuthority>): Collection<GrantedAuthority> {
        return defaultRoles + authorities
    }
}
