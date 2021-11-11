plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "nl.demonstratie.conventions"
version = "1.1.0"

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation ("org.gradle.crypto.checksum:org.gradle.crypto.checksum.gradle.plugin:1.2.0")
}
