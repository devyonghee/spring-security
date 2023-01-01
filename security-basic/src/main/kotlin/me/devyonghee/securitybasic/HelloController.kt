package me.devyonghee.securitybasic

import org.springframework.security.concurrent.DelegatingSecurityContextCallable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import java.util.concurrent.Executors

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        return "hello ${authentication.name}"
    }

    @GetMapping("/ciao")
    fun ciao(): String {
        val task = Callable {
            SecurityContextHolder.getContext().authentication.name
        }
        val threadPool = Executors.newCachedThreadPool()
        try {
            val contextCallable = DelegatingSecurityContextCallable(task)
            return "Ciao, ${threadPool.submit(contextCallable).get()} !"
        } finally {
            threadPool.shutdown()
        }
    }
}
