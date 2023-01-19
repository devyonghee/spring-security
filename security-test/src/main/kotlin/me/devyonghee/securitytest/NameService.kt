package me.devyonghee.securitytest

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class NameService {

    @PreAuthorize("hasAuthority('read')")
    fun name(): String {
        return "name"
    }
}

