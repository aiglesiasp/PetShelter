package es.aiglesiasp.petshelter.data.repositories

import es.aiglesiasp.petshelter.data.datasources.PetLocalDataSource
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository
import es.aiglesiasp.petshelter.framework.database.toDomain
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(
    private val petsLocalDataSource: PetLocalDataSource
): PetsRepository {
    override suspend fun getPets(): List<Pet> {
        val result = petsLocalDataSource.getPets()
        return result.map { it.toDomain() }
    }

    override suspend fun getPetById(id: Int): Pet? {
        val result = petsLocalDataSource.getPetById(id)
        return result?.toDomain()
    }

    override suspend fun getPetsByRefugio(refugio: String): List<Pet> {
        val result = petsLocalDataSource.getPetsByRefugio(refugio)
        return result.map { it.toDomain() }
    }
}