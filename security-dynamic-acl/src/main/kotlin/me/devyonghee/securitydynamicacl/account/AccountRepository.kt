package me.devyonghee.securitydynamicacl.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Int>