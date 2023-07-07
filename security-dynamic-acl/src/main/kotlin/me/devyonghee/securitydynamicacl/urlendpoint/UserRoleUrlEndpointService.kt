package me.devyonghee.securitydynamicacl.urlendpoint

import me.devyonghee.securitydynamicacl.account.UserRoleUrlEndpoint
import me.devyonghee.securitydynamicacl.urlendpoint.UserRoleUrlEndpointRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserRoleUrlEndpointService(
    private val repository: UserRoleUrlEndpointRepository,
) {

    fun all(): Collection<UserRoleUrlEndpoint> {
        return repository.findAll()
    }
}