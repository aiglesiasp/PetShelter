package es.aiglesiasp.petshelter.data.repositories

import es.aiglesiasp.petshelter.data.datasources.LoginLocalDataSource
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginLocalDataSource: LoginLocalDataSource
): LoginRepository {
    override suspend fun onLoginClick(
        email: String,
        password: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun onRegisterClick(
        name: String,
        email: String,
        role: String,
        password: String
    ): Boolean {
        TODO("Not yet implemented")
    }
}