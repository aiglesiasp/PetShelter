package es.aiglesiasp.petshelter.data.datasources

interface LoginLocalDataSource {
    suspend fun onLoginClick(email: String, password: String): Boolean
    suspend fun onRegisterClick(name: String, email: String, role: String, password: String): Boolean
}