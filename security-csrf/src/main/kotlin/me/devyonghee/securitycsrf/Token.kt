package me.devyonghee.securitycsrf

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Token(
    val identifier: String,
    var token: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {

    fun changeToken(token: String) {
        this.token = token
    }
}
