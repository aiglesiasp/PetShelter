package es.aiglesiasp.petshelter.data.datasources

import es.aiglesiasp.petshelter.framework.database.PetLocal

interface PetLocalDataSource {
    suspend fun getPets(): List<PetLocal>
}