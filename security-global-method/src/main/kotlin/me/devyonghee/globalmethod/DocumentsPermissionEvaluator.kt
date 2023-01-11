package me.devyonghee.globalmethod

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable

//@Component
class DocumentsPermissionEvaluator : PermissionEvaluator {

    override fun hasPermission(
        authentication: Authentication,
        target: Any,
        permission: Any
    ): Boolean {
        val admin = authentication.authorities
            .stream()
            .anyMatch {
                it.authority == permission.toString()
            }
        return admin || (target as Document).owner == authentication.name
    }

    override fun hasPermission(
        authentication: Authentication,
        id: Serializable,
        type: String,
        permission: Any
    ): Boolean {
        return false
    }
}
