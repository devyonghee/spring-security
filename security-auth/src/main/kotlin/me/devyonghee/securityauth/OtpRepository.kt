package me.devyonghee.securityauth

import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository : JpaRepository<Otp, String> {

    fun findByUsername(userName: String): Otp?
}
