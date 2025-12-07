package es.aiglesiasp.petshelter.framework.datasources

import es.aiglesiasp.petshelter.data.datasources.LoginLocalDataSource
import es.aiglesiasp.petshelter.framework.database.LoginDao
import es.aiglesiasp.petshelter.framework.database.LoginLocal
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(
    private val loginDao: LoginDao
): LoginLocalDataSource {
    override suspend fun checkUserByEmail(
        email: String,
    ): LoginLocal? {
        val user = loginDao.checkUserByEmail(email)
        return user
        //return loginDao.checkUserByEmail(email)
    }

    override suspend fun onRegisterClick(
        name: String,
        email: String,
        role: String,
        password: String
    ): Boolean {
        val id = loginDao.getMaxUserId() ?: 1
        val newUser = LoginLocal(
            id = id,
            nombre = name,
            email = email,
            role = role,
            password = password
        )

        loginDao.insertUser(newUser)
        return getUserById(id) != null
    }

    override suspend fun getUserById(id: Int): LoginLocal? {
        return loginDao.getUserById(id)
    }
}