package me.devyonghee.globalmethod

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class DocumentsPermissionIdEvaluator(
    private val documentRepository: DocumentRepository
) : PermissionEvaluator {

    override fun hasPermission(
        authentication: Authentication,
        target: Any,
        permission: Any
    ): Boolean {
        return false
    }

    override fun hasPermission(
        authentication: Authentication,
        id: Serializable,
        type: String,
        permission: Any
    ): Boolean {
        val document = documentRepository.findDocument(id.toString())
        val isAdmin = authentication.authorities.any { it.authority == permission.toString() }
        return isAdmin || document.owner == authentication.name
    }
}
