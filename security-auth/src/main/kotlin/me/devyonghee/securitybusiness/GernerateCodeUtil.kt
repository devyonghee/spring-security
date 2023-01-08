package me.devyonghee.securitybusiness

import java.security.SecureRandom

fun generateCode(): String {
    val random = SecureRandom.getInstanceStrong()
    return (random.nextInt(9000) + 1000).toString();
}
