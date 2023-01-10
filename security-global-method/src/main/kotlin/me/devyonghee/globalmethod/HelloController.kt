package me.devyonghee.globalmethod

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    private val nameService: NameService
) {
    @GetMapping("/hello")
    fun hello(): String {
        return nameService.name()
    }

    @GetMapping("/secret/names/{name}")
    fun names(@PathVariable name: String): List<String> {
        return nameService.getSecretNames(name);
    }
}
