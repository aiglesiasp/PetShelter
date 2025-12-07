package es.aiglesiasp.petshelter.ui.screens.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import es.aiglesiasp.petshelter.domain.repositories.SharedPreferencesRepository
import es.aiglesiasp.petshelter.framework.database.LoginLocal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = true,
        val user: LoginLocal? = null,
        val showLogoutDialog: Boolean = false,
        val showWarningDialog: Boolean = false
    )

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch {
            val profileId = sharedPreferencesRepository.getProfileId()
            setData(profileId)
        }
    }

    suspend fun setData(profileId: Int) {
        val user = loginRepository.getUserById(profileId)
        _uiState.value = UiState(isLoading = false, user = user)
    }

    fun onLogoutClick() {
        _uiState.value = _uiState.value.copy(showLogoutDialog = true)
    }

    fun onConfirmLogout() {
        _uiState.value = _uiState.value.copy(showLogoutDialog = false)
        viewModelScope.launch {
            sharedPreferencesRepository.clearAutologin()
            sharedPreferencesRepository.clearProfileId()
        }
    }

    fun dismissAlertLogout() {
        _uiState.value = _uiState.value.copy(showLogoutDialog = false)
    }

    fun showWarningDialog() {
        _uiState.value = _uiState.value.copy(showWarningDialog = true)
    }

    fun dismissWarningDialog() {
        _uiState.value = _uiState.value.copy(showWarningDialog = false)
    }
}
