import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.crypto.checksum.Checksum
import org.gradle.crypto.checksum.ChecksumPlugin
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*

// Extension functions grouped under a nice namespace to not lose track.
object DemoJavaConventions {
    fun Project.includeLegacyJunit() {
        dependencies {
            // quoted/dynamic because testImplementation is not available in this context (java plugin provides it)
            "testImplementation"("junit:junit:4.12") {
                because("Enabled support for legacy jUnit 4 tests via convention")
            }
        }
    }

    fun Project.defaultRepositories() {
        repositories {
            mavenLocal()
            mavenCentral()
        }
    }

    fun Project.generateChecksums() {
        apply<ChecksumPlugin>()

        this.tasks.register<Checksum>("createChecksums") {
            val extensions = listOf(".jar", ".war", ".ear")
            val libsDirectory = "${project.buildDir}/libs"
            files = project.fileTree(libsDirectory)
                .filter { file -> extensions.any { file.name.endsWith(it) } }
            outputDir = file(libsDirectory)
            algorithm = Checksum.Algorithm.SHA512
        }
    }

    fun Project.generateManifest(
        mainClassName: String? = null
    ) {
        apply<JavaPlugin>()

        tasks.named<Jar>("jar") {
            manifest {
                attributes(
                    listOfNotNull(
                        "Implementation-Title" to project.name,
                        "Implementation-Version" to project.version,
                        mainClassName?.let { "Main-Class" to it }
                    ).toMap()
                )
            }
        }
    }
}
