package me.devyonghee.globalmethod

import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentRepository: DocumentRepository
) {

    @PostAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    fun getDocument(code: String): Document {
        return documentRepository.findDocument(code)
    }
}
