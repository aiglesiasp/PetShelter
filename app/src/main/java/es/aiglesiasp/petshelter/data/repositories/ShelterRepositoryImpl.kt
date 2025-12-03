package es.aiglesiasp.petshelter.data.repositories

import es.aiglesiasp.petshelter.data.datasources.ShelterLocalDataSource
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.domain.repositories.ShelterRepository
import es.aiglesiasp.petshelter.framework.database.toDomain
import javax.inject.Inject

class ShelterRepositoryImpl @Inject constructor(
    private val shelterLocalDataSource: ShelterLocalDataSource
): ShelterRepository {
    override suspend fun getShelters(): List<Shelter> {
        val result = shelterLocalDataSource.getShelters()
        return result.map { it.toDomain() }
    }

    override suspend fun getShelterById(shelterId: Int): Shelter? {
        val result = shelterLocalDataSource.getShelterById(shelterId)
        return result?.toDomain()
    }
}