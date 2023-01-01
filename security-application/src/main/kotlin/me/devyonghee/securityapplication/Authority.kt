package me.devyonghee.securityapplication

import jakarta.persistence.*

@Entity
class Authority(

    val name: String,

    @JoinColumn(name = "user")
    @ManyToOne
    val user: User,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null
) {
}
