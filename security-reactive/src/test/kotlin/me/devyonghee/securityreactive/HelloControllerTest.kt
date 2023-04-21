package me.devyonghee.securityreactive

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class HelloControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun helloWithValidUser() {
        client.get()
            .uri("/hello")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun helloWithValidMockUser() {
        client.mutateWith(mockUser().roles("ADMIN"))
            .get()
            .uri("/hello")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun helloWithCsrf() {
        client.mutateWith(csrf())
            .mutateWith(mockUser().roles("ADMIN"))
            .post()
            .uri("/hello")
            .exchange()
            .expectStatus().isOk
    }
}
