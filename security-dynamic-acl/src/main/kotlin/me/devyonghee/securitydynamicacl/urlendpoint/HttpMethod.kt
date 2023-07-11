package me.devyonghee.securitydynamicacl.urlendpoint

enum class HttpMethod {
    ALL,
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE
    ;

    val isAll: Boolean by lazy { this == ALL }
}
