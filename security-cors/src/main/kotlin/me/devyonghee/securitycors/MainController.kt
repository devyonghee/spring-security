package me.devyonghee.securitycors

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    fun main(): String {
        return "main"
    }

    @ResponseBody
    @PostMapping("/test")
    @CrossOrigin("http://localhost:8080")
    fun test(): String {
        logger.info("test method called")
        return "Hello"
    }
}
