package me.devyonghee.securityapplication.domain

import jakarta.persistence.*

@Entity
class Authority(

    val name: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserAccount,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
}
