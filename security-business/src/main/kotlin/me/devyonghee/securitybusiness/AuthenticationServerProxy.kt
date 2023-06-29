package me.devyonghee.securitybusiness

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class AuthenticationServerProxy(
    private val rest: RestTemplate,
    @Value("\${authentication.server.url}")
    private val baseUrl: String
) {

    fun sendAuth(username: String, password: String) {
        rest.postForEntity(
            "$baseUrl/user/auth",
            HttpEntity<User>(User(username, password)),
            Unit.javaClass
        )
    }

    fun sendOTP(username: String, code: String): Boolean {
        return rest.postForEntity(
            "$baseUrl/otp/check",
            HttpEntity<User>(User(username, code = code)),
            Unit.javaClass
        ).statusCode
            .is2xxSuccessful
    }
}
