package me.devyonghee.securitydynamicacl.urlendpoint.domain

import org.springframework.data.jpa.repository.JpaRepository

interface WhiteUrlEndpointRepository : JpaRepository<WhiteUrlEndpoint, Int>