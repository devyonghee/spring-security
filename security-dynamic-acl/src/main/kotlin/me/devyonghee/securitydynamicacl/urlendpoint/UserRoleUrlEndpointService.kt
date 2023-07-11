package me.devyonghee.securitydynamicacl.urlendpoint

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserRoleUrlEndpointService(
    private val repository: UserRoleUrlEndpointRepository
) {

    fun all(): Collection<UserRoleUrlEndpoint> {
        return repository.findAll()
    }
}
