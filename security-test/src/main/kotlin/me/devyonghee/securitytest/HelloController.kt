package me.devyonghee.securitytest

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(auth: Authentication): String {
        return "Hello, ${auth.name}!"
    }
}
