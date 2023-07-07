package me.devyonghee.securitydynamicacl.book

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val bookService: BookService) {

    @PostMapping("/books")
    fun createBook(@RequestBody request: BookRequest): ResponseEntity<Void> {
        bookService.save(request)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/books/{id}")
    fun book(@PathVariable id: Int): ResponseEntity<Book> {
        return ResponseEntity.ok(bookService.book(id))
    }

    @GetMapping("/books")
    fun books(): ResponseEntity<Collection<Book?>?> {
        return ResponseEntity.ok(bookService.books())
    }

    class BookRequest(
        val title: String,
        val body: String,
    )
}
