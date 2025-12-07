package es.aiglesiasp.petshelter.domain.repositories

import es.aiglesiasp.petshelter.domain.model.Pet

interface PetsRepository {
    suspend fun getPets(): List<Pet>
    suspend fun getPetById(id: Int): Pet?
    suspend fun getPetsByRefugio(refugio: String): List<Pet>
}