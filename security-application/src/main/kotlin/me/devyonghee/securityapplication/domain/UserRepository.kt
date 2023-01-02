package me.devyonghee.securityapplication.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserAccount, Int> {

    fun findUserByUsername(u: String): UserAccount?
}
