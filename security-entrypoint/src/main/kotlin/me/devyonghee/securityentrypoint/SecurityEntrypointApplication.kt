package me.devyonghee.securityentrypoint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityEntrypointApplication

fun main(args: Array<String>) {
    runApplication<SecurityEntrypointApplication>(*args)
}
