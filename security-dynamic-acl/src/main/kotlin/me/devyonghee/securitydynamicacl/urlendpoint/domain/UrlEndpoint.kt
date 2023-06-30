package me.devyonghee.securitydynamicacl.urlendpoint.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "url_endpoint")
class UrlEndpoint(
    @Enumerated(EnumType.STRING)
    val httpMethod: HttpMethod,
    val urlPattern: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
)