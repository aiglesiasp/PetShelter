package es.aiglesiasp.petshelter.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShelterDao {
    @Query("SELECT * FROM shelters")
    suspend fun getShelters(): List<ShelterLocal>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertShelters(characters: List<ShelterLocal>)

    @Query("SELECT * FROM shelters WHERE id = :id" )
    suspend fun getSheltersById(id: Int): ShelterLocal?
}