package me.devyonghee.securityreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityReactiveApplication

fun main(args: Array<String>) {
    runApplication<SecurityReactiveApplication>(*args)
}
