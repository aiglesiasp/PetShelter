package es.aiglesiasp.petshelter.data.datasources

import es.aiglesiasp.petshelter.framework.database.ShelterLocal

interface ShelterLocalDataSource {
    suspend fun getShelters(): List<ShelterLocal>
}