package me.devyonghee.securitydynamicacl.account

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import me.devyonghee.securitydynamicacl.urlendpoint.HttpMethod
import me.devyonghee.securitydynamicacl.urlendpoint.UrlEndpoint

@Entity
@Table(name = "user_role_url_endpoint")
class UserRoleUrlEndpoint(
    @ManyToOne(optional = false)
    val urlEndpoint: UrlEndpoint,

    @ManyToOne(optional = false)
    val userRole: UserRole,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
) {
    val httpMethod: HttpMethod = urlEndpoint.httpMethod
    val urlPattern: String = urlEndpoint.urlPattern
    val role: UserRole.Role = userRole.role
}