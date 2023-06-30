package me.devyonghee.securitydynamicacl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class SecurityConfigTest{

    @Test
    fun test(){

        print(BCryptPasswordEncoder().encode("password"))
    }
}
