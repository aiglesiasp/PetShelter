package es.aiglesiasp.petshelter.domain.repositories

interface SharedPreferencesRepository {
    suspend fun saveAutologin(autologin: Boolean)
    suspend fun getAutologin(): Boolean
    suspend fun clearAutologin()
    suspend fun clearAll()
}
