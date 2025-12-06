package es.aiglesiasp.petshelter.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val loginResult: LoginResult = LoginResult.Idle
    )

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email, loginResult = LoginResult.Idle)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password, loginResult = LoginResult.Idle)
    }

    fun onLoginClick() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = loginRepository.onLoginClick(
                email = _uiState.value.email,
                password = _uiState.value.password
            )
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                loginResult = result
            )
        }
    }
}