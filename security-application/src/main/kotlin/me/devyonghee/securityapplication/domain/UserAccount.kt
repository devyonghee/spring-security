package me.devyonghee.securityapplication.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class UserAccount(
    val username: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    val encryptionAlgorithm: EncryptionAlgorithm,

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    val authorities: List<Authority>,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)
