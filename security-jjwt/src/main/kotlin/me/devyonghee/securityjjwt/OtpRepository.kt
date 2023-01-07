package me.devyonghee.securityjjwt

import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository : JpaRepository<Otp, String> {

    fun findByUsername(userName: String): Otp?
}
