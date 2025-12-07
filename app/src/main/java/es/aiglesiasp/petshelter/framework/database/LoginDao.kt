package es.aiglesiasp.petshelter.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUsers(characters: List<LoginLocal>)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(characters: LoginLocal)

    @Query("SELECT * FROM login WHERE email = :email LIMIT 1")
    suspend fun checkUserByEmail(email: String): LoginLocal?

    @Query("SELECT * FROM login WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: Int): LoginLocal?

    @Query("SELECT MAX(id) FROM login")
    suspend fun getMaxUserId(): Int?
}