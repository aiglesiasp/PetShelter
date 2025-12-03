package es.aiglesiasp.petshelter.framework.datasources

import es.aiglesiasp.petshelter.data.datasources.LoginLocalDataSource
import es.aiglesiasp.petshelter.framework.database.LoginDao
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val loginDao: LoginDao
): LoginLocalDataSource {
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