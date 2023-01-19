package me.devyonghee.securitytest

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
class NameServiceTest {

    @Autowired
    private lateinit var nameService: NameService

    @Test
    fun nameWithoutUser() {
        assertThatThrownBy { nameService.name() }
            .isInstanceOf(AuthenticationException::class.java)
    }

    @Test
    @WithMockUser(authorities = ["read"])
    fun nameWithReadUser() {
        assertThat(nameService.name()).isEqualTo("name")
    }

    @Test
    @WithMockUser(authorities = ["write"])
    fun nameWithWriteUser() {
        assertThatThrownBy { nameService.name() }
            .isInstanceOf(AccessDeniedException::class.java)
    }
}
