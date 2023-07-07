package me.devyonghee.securitydynamicacl.urlendpoint

import me.devyonghee.securitydynamicacl.account.UserRoleUrlEndpoint
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleUrlEndpointRepository: JpaRepository<UserRoleUrlEndpoint, Int> {
}