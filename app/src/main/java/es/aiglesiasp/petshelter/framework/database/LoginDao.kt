package es.aiglesiasp.petshelter.framework.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LoginDao {
    @Query("SELECT * FROM login WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): LoginLocal
}