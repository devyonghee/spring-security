package me.devyonghee.securityauth

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserAccount, String> {

    fun findByUsername(userName: String): UserAccount?
}
