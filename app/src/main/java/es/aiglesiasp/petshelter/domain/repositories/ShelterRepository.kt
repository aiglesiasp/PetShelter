package es.aiglesiasp.petshelter.domain.repositories

import es.aiglesiasp.petshelter.domain.model.Shelter

interface ShelterRepository {
    suspend fun getShelters(): List<Shelter>
}