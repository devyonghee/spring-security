package me.devyonghee.securitytest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun helloAuthenticationWithValidUser() {
        mvc.perform(
            get("/hello")
                .with(httpBasic("readUser", "password"))
        ).andExpect(status().isOk)
    }

    @Test
    fun helloAuthenticationWithInvalidUser() {
        mvc.perform(
            get("/hello")
                .with(httpBasic("any", "password"))
        ).andExpect(status().isUnauthorized)
    }

    @Test
    fun loggingInWithWrongUser() {
        mvc.perform(
            formLogin()
                .user("readUser").password("password")
        ).andExpect(redirectedUrl("/home"))
            .andExpect(status().isFound)
            .andExpect(authenticated())
    }

    @Test
    fun loggingInWithCorrectAuthority() {
        mvc.perform(
            formLogin()
                .user("writeUser").password("password")
        ).andExpect(redirectedUrl("/error"))
            .andExpect(status().isFound)
            .andExpect(authenticated())
    }
}
