package es.aiglesiasp.petshelter.framework.datasources

import es.aiglesiasp.petshelter.data.datasources.ShelterLocalDataSource
import es.aiglesiasp.petshelter.framework.database.ShelterDao
import es.aiglesiasp.petshelter.framework.database.ShelterLocal
import javax.inject.Inject

class ShelterLocalDataSourceImpl @Inject constructor(
    private val shelterDao: ShelterDao
): ShelterLocalDataSource {
    override suspend fun getShelters(): List<ShelterLocal> {
        return shelterDao.getShelters()
    }

    override suspend fun getShelterById(shelterId: Int): ShelterLocal? {
        return shelterDao.getSheltersById(shelterId)
    }
}