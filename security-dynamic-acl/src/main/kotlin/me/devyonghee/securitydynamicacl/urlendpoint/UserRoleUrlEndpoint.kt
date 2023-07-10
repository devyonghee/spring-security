package me.devyonghee.securitydynamicacl.urlendpoint

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "user_role_url_endpoint")
class UserRoleUrlEndpoint(
    @ManyToOne(optional = false)
    @JoinColumn(name = "url_endpoint_id")
    val urlEndpoint: UrlEndpoint,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role")
    val userRole: UserRole,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @Column(name = "user_role_url_endpoint_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
) {
    val httpMethod: HttpMethod
        get() = urlEndpoint.httpMethod

    val urlPattern: String
        get() = urlEndpoint.urlPattern

    val role: UserRole.Role
        get() = userRole.role
}