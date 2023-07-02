package me.devyonghee.securitydynamicacl.urlendpoint.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "white_url_endpoint")
class WhiteUrlEndpoint(
    @ManyToOne(optional = false)
    @JoinColumn(name = "url_endpoint_id")
    val urlEndpoint: UrlEndpoint,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
) {

}