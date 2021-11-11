package nl.demonstratie.petclinic.backend

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import nl.demonstratie.petclinic.core.Pet
import nl.demonstratie.petclinic.core.PetRepository
import java.util.*

class InMemoryPetService(pets: List<Pet>) : PetRepository {
    private val store = pets
        .map { it.id to it }
        .toMap(LinkedHashMap())

    override suspend fun findPetById(id: UUID): Pet? {
        return store[id]
    }

    override fun streamAllPets(): Flow<Pet> {
        return store.values.asFlow()
    }

    override suspend fun savePet(pet: Pet) {
        store[pet.id] = pet
    }

    override suspend fun deletePet(pet: Pet) {
        store.remove(pet.id)
    }
}
