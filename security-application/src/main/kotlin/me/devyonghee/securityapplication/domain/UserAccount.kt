package me.devyonghee.securityapplication.domain

import jakarta.persistence.*

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
) {
}
