package me.devyonghee.securitydynamicacl.urlendpoint.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_role")
class UserRole(
    @Id
    @Enumerated(EnumType.STRING)
    val role: Role,
) {
    enum class Role {
        ADMIN,
        CLIENT
    }
}