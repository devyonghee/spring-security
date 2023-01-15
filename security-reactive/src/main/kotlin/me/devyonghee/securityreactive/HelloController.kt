package me.devyonghee.securityreactive

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(auth: Mono<Authentication>): Mono<String> {
        return auth.map { "Hello ${it.name}" }
    }

    @GetMapping("/helloContext")
    fun helloContext(): Mono<String> {
        return ReactiveSecurityContextHolder.getContext()
            .map { it.authentication }
            .map { "Hello ${it.name}" }
    }
}
