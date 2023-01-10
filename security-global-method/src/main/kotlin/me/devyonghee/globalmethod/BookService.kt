package me.devyonghee.globalmethod

import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service

@Service
class BookService {
    private val records = mapOf(
        "readUser" to Employee(
            "Emma Thompson",
            listOf("karamazov brothers"),
            listOf("accountant", "reader")
        ),
        "writeUser" to Employee(
            "Natalie Parker",
            listOf("Beautiful Paris"),
            listOf("researcher")
        )
    )

    @PostAuthorize("returnObject.roles.contains('reader')")
    fun getBooksDetail(name: String): Employee {
        return records[name]!!
    }
}
