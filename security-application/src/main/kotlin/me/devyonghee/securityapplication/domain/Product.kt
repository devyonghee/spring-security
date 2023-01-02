package me.devyonghee.securityapplication.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class Product(
    val name: String,
    val price: BigDecimal,
    @Enumerated(EnumType.STRING)
    val currency: Currency,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {

}
