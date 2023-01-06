package me.devyonghee.securitycors

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityCsrfApplication

fun main(args: Array<String>) {
    runApplication<SecurityCsrfApplication>(*args)
}
