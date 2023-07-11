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
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.options
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun helloUnauthenticated() {
        mockMvc.get("/hello")
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    @WithMockUser
    fun helloAuthenticated() {
        mockMvc.get("/hello")
            .andExpect {
                status { isOk() }
                content { string("Hello, user!") }
            }
    }

    @Test
    @WithMockUser(username = "any")
    fun helloAuthenticatedAny() {
        mockMvc.get("/hello")
            .andExpect {
                status { isOk() }
                content { string("Hello, any!") }
            }
    }

    @Test
    fun helloAuthenticatedWithAny() {
        mockMvc.get("/hello") {
            with(user("any"))
        }
            .andExpect {
                status { isOk() }
                content { string("Hello, any!") }
            }
    }

    @Test
    @WithUserDetails("readUser")
    fun helloAuthenticatedReadUser() {
        mockMvc.get("/hello")
            .andExpect {
                status { isOk() }
                content { string("Hello, readUser!") }
            }
    }

    @Test
    @WithCustomUser("readUser")
    fun helloCustomAuthenticatedReadUser() {
        mockMvc.get("/hello")
            .andExpect {
                status { isOk() }
                content { string("Hello, readUser!") }
            }
    }

    @Test
    fun testHelloPOST() {
        mockMvc.post("/hello")
            .andExpect {
                status { isForbidden() }
            }
    }

    @Test
    fun testHelloPOSTWithCSRF() {
        mockMvc.post("/hello") {
            with(csrf())
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun testCORSForTestEndpoint() {
        mockMvc.options("/test") {
            header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST")
            header(HttpHeaders.ORIGIN, "https://www.example.com")
        }.andExpect {
            header {
                HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN
                HttpHeaders.ALLOW
            }
        }
    }
}
