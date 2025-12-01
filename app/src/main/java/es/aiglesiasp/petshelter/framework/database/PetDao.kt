package es.aiglesiasp.petshelter.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PetDao {
    @Query("SELECT * FROM pets")
    suspend fun getPets(): List<PetLocal>

    @Query("SELECT * FROM pets WHERE id = :id" )
    suspend fun getPetById(id: Int): PetLocal?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPets(characters: List<PetLocal>)

    @Query("SELECT * FROM pets WHERE nombre = :name" )
    suspend fun getPetByName(name: String): List<PetLocal>
}