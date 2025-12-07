package es.aiglesiasp.petshelter.framework.datasources

import es.aiglesiasp.petshelter.data.datasources.PetLocalDataSource
import es.aiglesiasp.petshelter.framework.database.PetDao
import es.aiglesiasp.petshelter.framework.database.PetLocal
import javax.inject.Inject

class PetLocalDataSourceImpl @Inject constructor(
    private val petDao: PetDao
): PetLocalDataSource {
    override suspend fun getPets(): List<PetLocal> {
        return petDao.getPets()
    }

    override suspend fun getPetById(id: Int): PetLocal? {
        return petDao.getPetById(id)

    }

    override suspend fun getPetsByRefugio(refugio: String): List<PetLocal> {
        return petDao.getPetsByRefugio(refugio)
    }
}