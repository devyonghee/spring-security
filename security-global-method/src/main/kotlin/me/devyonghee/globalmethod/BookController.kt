package me.devyonghee.globalmethod

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val bookService: BookService
) {

    @GetMapping("/book/details/{name}")
    fun getDetails(@PathVariable name: String): Employee {
        return bookService.getBooksDetail(name)
    }
}
