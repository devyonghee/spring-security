package me.devyonghee.securitycsrf

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun getHello(): String {
        return "get hello"
    }

    @PostMapping("/hello")
    fun postHello(): String {
        return "post hello"
    }
}
