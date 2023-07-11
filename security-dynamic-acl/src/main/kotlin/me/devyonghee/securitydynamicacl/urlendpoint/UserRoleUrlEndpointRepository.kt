package me.devyonghee.securitydynamicacl.urlendpoint

import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleUrlEndpointRepository : JpaRepository<UserRoleUrlEndpoint, Int>
