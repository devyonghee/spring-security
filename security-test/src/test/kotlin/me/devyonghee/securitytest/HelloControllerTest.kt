package me.devyonghee.securitytest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithUserDetails
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
    @WithMockUser(username = "any")
    fun helloAuthenticatedAny() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello, any!"))
            .andExpect(status().isOk)
    }

    @Test
    fun helloAuthenticatedWithAny() {
        mockMvc.perform(get("/hello").with(user("any")))
            .andExpect(content().string("Hello, any!"))
            .andExpect(status().isOk)
    }

    @Test
    @WithUserDetails("readUser")
    fun helloAuthenticatedReadUser() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello, readUser!"))
            .andExpect(status().isOk)
    }

    @Test
    @WithCustomUser("readUser")
    fun helloCustomAuthenticatedReadUser() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello, readUser!"))
            .andExpect(status().isOk)
    }

    @Test
    fun testHelloPOST() {
        mockMvc.perform(post("/hello"))
            .andExpect(status().isForbidden)
    }

    @Test
    fun testHelloPOSTWithCSRF() {
        mockMvc.perform(post("/hello").with(csrf()))
            .andExpect(status().isOk)
    }

    @Test
    fun testCORSForTestEndpoint() {
        mockMvc.perform(
            options("/test")
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST")
                .header(HttpHeaders.ORIGIN, "https://www.example.com")
        ).andExpect(header().exists(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN))
            .andExpect(header().exists(HttpHeaders.ALLOW))
    }
}
