plugins {
    `maven-publish`
    application
    kotlin("jvm")
    id("nl.demonstratie.kotlin-conventions")
}

val kotlinVersion = "1.5.31"
val ktorVersion = "1.6.5"
val main = "nl.demonstratie.petclinic.backend.MainKt"
version = "1.6.0"

// Extensions functions are wrapped in a namespace object but don't have to be.
with(DemoJavaConventions) {
    defaultRepositories()
    generateChecksums()
    generateManifest()
}

application {
    mainClass.set(main)
}

dependencies {
    implementation(project(":core"))

    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-metrics-micrometer:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")


    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.+")
    implementation("io.github.microutils:kotlin-logging:2.0.+")
    implementation("org.slf4j:slf4j-api:1.7.+")

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.+")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.+")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.+")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.+")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
