package es.aiglesiasp.petshelter.data.datasources

import es.aiglesiasp.petshelter.framework.database.LoginLocal

interface LoginLocalDataSource {
    suspend fun checkUserByEmail(email: String): LoginLocal?
    suspend fun onRegisterClick(name: String, email: String, role: String, password: String): Boolean
    suspend fun getUserById(id: Int): LoginLocal?
}