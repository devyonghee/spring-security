package me.devyonghee.securityfilter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityFilterApplication

fun main(args: Array<String>) {
    runApplication<SecurityFilterApplication>(*args)
}
