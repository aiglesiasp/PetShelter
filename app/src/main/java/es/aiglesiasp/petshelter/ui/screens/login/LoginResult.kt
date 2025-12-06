package es.aiglesiasp.petshelter.ui.screens.login

import es.aiglesiasp.petshelter.framework.database.LoginLocal


sealed class LoginResult {
    object EmailNotFound : LoginResult()
    object InvalidPassword : LoginResult()
    data class Success(val user: LoginLocal) : LoginResult()
    object Idle: LoginResult()
}