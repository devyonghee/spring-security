package me.devyonghee.securitytest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun helloUnauthenticated() {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser
    fun helloAuthenticated() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello, user!"))
            .andExpect(status().isOk)
    }

    @Test
    @WithMockUser(username = "mary")
    fun helloAuthenticatedMary() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello, mary!"))
            .andExpect(status().isOk)
    }

    @Test
    fun helloAuthenticatedWithMary() {
        mockMvc.perform(get("/hello").with(user("mary")))
            .andExpect(content().string("Hello, mary!"))
            .andExpect(status().isOk)
    }
}
