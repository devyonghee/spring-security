package me.devyonghee.securitybusiness

import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository : JpaRepository<Otp, String> {

    fun findByUsername(userName: String): Otp?
}
