package me.devyonghee.securityapplication.application

import me.devyonghee.securityapplication.domain.CustomUserDetails
import me.devyonghee.securityapplication.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
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
