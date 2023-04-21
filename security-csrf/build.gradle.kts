plugins {
    id("org.jetbrains.kotlin.plugin.jpa") version ("1.6.21")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.flywaydb:flyway-mysql")
    runtimeOnly("com.mysql:mysql-connector-j")
}
