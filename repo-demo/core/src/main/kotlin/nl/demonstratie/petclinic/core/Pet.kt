package nl.demonstratie.petclinic.core

import java.util.*

data class Pet(val id: UUID, val name: String) {
    init {
        require(name.length > 3)
    }
}
