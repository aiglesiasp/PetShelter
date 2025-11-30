package es.aiglesiasp.petshelter.domain.repositories

import es.aiglesiasp.petshelter.domain.model.Pet

interface PetsRepository {
    suspend fun getPets(): List<Pet>
}