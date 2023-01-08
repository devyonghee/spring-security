package me.devyonghee.securityauth

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/user/add")
    fun addUser(@RequestBody userAccount: UserAccount) {
        userService.addUser(userAccount)
    }

    @PostMapping("/user/auth")
    fun auth(@RequestBody userAccount: UserAccount) {
        userService.auth(userAccount)
    }

    @PostMapping("/otp/check")
    fun check(@RequestBody otp: Otp, response: HttpServletResponse) {
        response.status = if (userService.check(otp)) {
            HttpServletResponse.SC_OK
        } else {
            HttpServletResponse.SC_FORBIDDEN
        }
    }
}
