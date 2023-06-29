package me.devyonghee.securityentrypoint

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun getHello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }

    @PostMapping("/hello")
    fun postHello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }
}
