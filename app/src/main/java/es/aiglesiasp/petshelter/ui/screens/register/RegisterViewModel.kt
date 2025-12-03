package es.aiglesiasp.petshelter.ui.screens.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val name: String = "",
        val email: String = "",
        val password: String = "",
        val role: String = "",
        val expanded: Boolean = false
    )

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun onRoleChange(role: String) {
        _uiState.value = _uiState.value.copy(role = role)
    }

    fun onExpandedChange(expanded: Boolean) {
        _uiState.value = _uiState.value.copy(expanded = expanded)
    }

    fun onRegisterClick() {
        _uiState.value = _uiState.value.copy(isLoading = true)
    }
}