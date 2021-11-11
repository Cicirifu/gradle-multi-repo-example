package nl.demonstratie.petclinic.backend

import java.util.*

fun String.toUUIDOrNull(): UUID? {
    return try {
        UUID.fromString(this)
    } catch (ex: Exception) {
        null
    }
}
