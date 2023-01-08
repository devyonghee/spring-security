package me.devyonghee.securityauth

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class UserAccount(
    @Id
    val username: String,
    var password: String
) {
    fun changePassword(password: String) {
        this.password = password
    }
}
