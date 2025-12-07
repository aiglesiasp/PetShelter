package es.aiglesiasp.petshelter.domain.repositories

import es.aiglesiasp.petshelter.framework.database.LoginLocal
import es.aiglesiasp.petshelter.ui.screens.login.LoginResult

interface LoginRepository {
    suspend fun onLoginClick(email: String, password: String): LoginResult
    suspend fun onRegisterClick(name: String, email: String, role: String, password: String): Boolean
    suspend fun getUserById(id: Int): LoginLocal?
}