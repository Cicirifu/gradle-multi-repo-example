plugins {
    kotlin("jvm")
    id("nl.demonstratie.kotlin-conventions")
}

version = "1.0.0"

// Extensions functions are wrapped in a namespace object but don't have to be.
with(DemoJavaConventions) {
    defaultRepositories()
    generateChecksums()
    includeLegacyJunit()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}

