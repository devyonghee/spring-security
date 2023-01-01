package me.devyonghee.securityapplication

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class JpaUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user = userRepository.findUserByUsername(username)
        return CustomUserDetails(
            user ?: throw UsernameNotFoundException("user not exists")
        )
    }
}
