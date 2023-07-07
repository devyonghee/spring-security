package me.devyonghee.securitydynamicacl.account

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "account_user_role")
class AccountUserRole(
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    val account: Account,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_role")
    val userRole: UserRole,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Id
    @Column(name = "account_user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
)