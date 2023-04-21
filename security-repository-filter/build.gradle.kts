plugins {
    id("org.jetbrains.kotlin.plugin.jpa") version ("1.6.21")
}

dependencies {
    implementation("org.springframework.security:spring-security-data")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.flywaydb:flyway-mysql")
}
