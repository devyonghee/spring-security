package me.devyonghee.securitycsrf

import org.springframework.data.jpa.repository.JpaRepository

interface JpaTokenRepository : JpaRepository<Token, Int> {

    fun findByIdentifier(identifier: String): Token?
}
