package me.devyonghee.securitybusiness

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Otp(
    @Id
    val username: String,
    var code: String
) {
    fun changeCode(code: String) {
        this.code = code
    }
}
