package es.aiglesiasp.petshelter.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUsers(characters: List<LoginLocal>)

    @Query("SELECT * FROM login WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): LoginLocal
}