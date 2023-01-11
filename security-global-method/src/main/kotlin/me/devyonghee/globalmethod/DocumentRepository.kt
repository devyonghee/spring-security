package me.devyonghee.globalmethod

import org.springframework.stereotype.Repository

@Repository
class DocumentRepository {
    private val documents = mapOf(
        "abc123" to Document("admin"),
        "qwe123" to Document("admin"),
        "asd555" to Document("manager"),
    )

    fun findDocument(code: String): Document {
        return documents[code]!!
    }
}
