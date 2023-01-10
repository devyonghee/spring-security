package me.devyonghee.globalmethod

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class GlobalMethodConfig(
    private val evaluator: DocumentsPermissionEvaluator
) {


}
