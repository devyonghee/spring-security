package me.devyonghee.securitydynamicacl.urlendpoint

import jakarta.persistence.Column
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
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @Column(name = "url_endpoint_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
)
