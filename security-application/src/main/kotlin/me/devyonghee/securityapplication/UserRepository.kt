package me.devyonghee.securityapplication

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

    fun findUserByUsername(u: String): User?
}
