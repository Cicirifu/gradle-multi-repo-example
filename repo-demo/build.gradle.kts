plugins {
    // Does nothing but put the plugin on the classpath, because the precompiled script is empty (`apply false` not required)
    id("nl.demonstratie.conventions") version "1.+" apply false
    kotlin("jvm") version "1.5.31" apply false
}
