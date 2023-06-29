package me.devyonghee.securityentrypoint

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun getHello() {
        mvc.get("/hello").andExpect {
            status { isOk() }
        }
    }

    @Test
    fun postHello() {
        mvc.post("/hello").andDo { print() }
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun postAnyAuthorityHello() {
        mvc.post("/hello") {
            header("Request-Id", "any")
        }.andDo { print() }
            .andExpect {
                status { isUnauthorized() }
            }
    }

    @Test
    fun postRequestAuthorityHello() {
        mvc.post("/hello") {
            header("Request-Id", "REQUEST_AUTHORITY")
        }.andDo { print() }
            .andExpect {
                status { isOk() }
            }
    }
}
