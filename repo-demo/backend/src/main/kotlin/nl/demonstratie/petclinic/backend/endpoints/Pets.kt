package nl.demonstratie.petclinic.backend.endpoints

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import nl.demonstratie.petclinic.backend.toUUIDOrNull
import nl.demonstratie.petclinic.core.Pet
import nl.demonstratie.petclinic.core.PetRepository

fun Route.pets(
    service: PetRepository
) {
    route("/pets") {
        get {
            val flow = service.streamAllPets()

            return@get call.respond(flow.take(25).toList())
        }

        get("{id}") {
            val id = call.parameters["id"]?.toUUIDOrNull() ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val pet = service.findPetById(id) ?: return@get call.respondText(
                "No pet with id $id",
                status = HttpStatusCode.NotFound
            )

            return@get call.respond(pet)
        }

        post {
            val pet = runCatching { call.receive<Pet>() }.getOrElse {
                return@post call.respondText(
                    "Error: ${it.message}",
                    status = HttpStatusCode.BadRequest
                )
            }

            service.savePet(pet)

            return@post call.respond(status = HttpStatusCode.Created, pet)
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toUUIDOrNull() ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val pet = service.findPetById(id) ?: return@delete call.respondText(
                "No pet with id $id",
                status = HttpStatusCode.NotFound
            )

            service.deletePet(pet)

            return@delete call.respond(HttpStatusCode.OK)
        }
    }
}
