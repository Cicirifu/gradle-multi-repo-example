package nl.demonstratie.petclinic.core

import kotlinx.coroutines.flow.Flow
import java.util.*

interface PetRepository {
    suspend fun findPetById(id: UUID): Pet?
    fun streamAllPets(): Flow<Pet>
    suspend fun savePet(pet: Pet)
    suspend fun deletePet(pet: Pet)
}
