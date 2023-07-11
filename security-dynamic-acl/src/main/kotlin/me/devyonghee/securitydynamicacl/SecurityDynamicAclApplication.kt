package me.devyonghee.securitydynamicacl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SecurityDynamicAclApplication

fun main(args: Array<String>) {
    runApplication<SecurityDynamicAclApplication>(*args)
}
