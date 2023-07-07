package me.devyonghee.securitydynamicacl.book

import me.devyonghee.securitydynamicacl.book.BookController.BookRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookService(private val bookRepository: BookRepository) {
    @Transactional
    fun save(book: BookRequest) {
        bookRepository.save(Book(book.title, book.body))
    }

    fun books(): List<Book> {
        return bookRepository.findAll()
    }

    fun book(id: Int): Book {
        return bookRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Book not found")
    }
}
