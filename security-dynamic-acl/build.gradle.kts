plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-docker-compose")

    runtimeOnly("mysql:mysql-connector-java")
    implementation("org.flywaydb:flyway-mysql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
