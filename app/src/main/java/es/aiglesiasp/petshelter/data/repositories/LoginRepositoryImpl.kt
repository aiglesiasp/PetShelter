package es.aiglesiasp.petshelter.data.repositories

import es.aiglesiasp.petshelter.data.datasources.LoginLocalDataSource
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import es.aiglesiasp.petshelter.ui.screens.login.LoginResult
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginLocalDataSource: LoginLocalDataSource
): LoginRepository {
    override suspend fun onLoginClick(
        email: String,
        password: String
    ): LoginResult {
        val user = loginLocalDataSource.checkUserByEmail(email)

        return when {
            user == null -> LoginResult.EmailNotFound
            user.password != password -> LoginResult.InvalidPassword
            else -> LoginResult.Success(user)
        }
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