package nl.demonstratie.petclinic.backend

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValues
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mu.KotlinLogging
import nl.demonstratie.petclinic.backend.endpoints.pets
import nl.demonstratie.petclinic.core.Pet
import java.io.File

val logger = KotlinLogging.logger {}

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        val petService = InMemoryPetService(pets = data("pets"))

        routing {
            route("/api/v1") {
                pets(petService)
            }
        }
    }.start(wait = true)
}

class Dummy
inline fun <reified T: Any> data(path: String): List<T> {
    val mapper = YAMLMapper().registerKotlinModule()
    val root = Dummy::class.java.classLoader.getResource(path)!!
    val rootFile = File(root.toURI())

    return rootFile.walkBottomUp()
        .filter { it.extension.lowercase() in listOf("yml", "yaml", "json") }
        .flatMap {
            logger.info { "Reading file: ${it.name}" }
            mapper
                .readValues<JsonNode>(mapper.factory.createParser(it))
                .readAll()
        }
        .filterIsInstance<ObjectNode>()
        .map { mapper.convertValue<T>(it) }
        .toList()
}
