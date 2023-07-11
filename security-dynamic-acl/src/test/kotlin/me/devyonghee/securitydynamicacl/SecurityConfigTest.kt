package me.devyonghee.securitydynamicacl

import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SecurityConfigTest {

    @Test
    fun test() {
        print(BCryptPasswordEncoder().encode("password"))
    }
}
