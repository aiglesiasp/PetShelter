package es.aiglesiasp.petshelter.framework

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ShelterDao {
    @Query("SELECT * FROM shelters")
    suspend fun getShelters(): List<ShelterLocal>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertShelters(characters: List<ShelterLocal>)

    @Query("SELECT * FROM shelters WHERE nombre = :name" )
    suspend fun getSheltersByName(name: String): List<ShelterLocal>

    @Transaction
    @Query("SELECT * FROM shelters")
    suspend fun getSheltersWithPets(): List<ShelterWithPets>

    @Transaction
    @Query("SELECT * FROM shelters WHERE id = :shelterId")
    suspend fun getShelterWithPetsById(shelterId: Int): ShelterWithPets?
}