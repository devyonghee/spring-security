package me.devyonghee.securityjjwt

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val otpRepository: OtpRepository
) {

    fun addUser(user: UserAccount) {
        user.changePassword(passwordEncoder.encode(user.password))
        userRepository.save(user)
    }

    fun auth(user: UserAccount) {
        userRepository.findByUsername(user.username)?.apply {
            if (!passwordEncoder.matches(user.password, password)) {
                throw BadCredentialsException("Bad credentials")
            }
            renewOtp(this)
        } ?: throw BadCredentialsException("Bad credentials")
    }

    private fun renewOtp(user: UserAccount) {
        val code = generateCode()
        otpRepository.findByUsername(user.username)?.apply {
            changeCode(code)
        } ?: apply {
            otpRepository.save(Otp(user.username, code))
        }
    }

    fun check(otp: Otp): Boolean {
        return otpRepository.findByUsername(otp.username)?.let {
            it.code == otp.code
        } ?: false
    }
}
