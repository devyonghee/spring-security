package me.devyonghee.globalmethod

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class NameService {

    @PreAuthorize("hasAuthority('read')")
    fun name(): String {
        return "name"
    }

    @PreAuthorize("#name == authentication.principal.username")
    fun getSecretNames(name: String): List<String> {
        val names = mapOf(
            "readUser" to listOf("readUser1", "readUser2"),
            "writeUser" to listOf("writeUser1", "writeUser2")
        )
        return names[name] ?: emptyList()
    }
}
