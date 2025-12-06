package es.aiglesiasp.petshelter.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.repositories.SharedPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val sharedPrefs: SharedPreferencesRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<LoginOrHome?>(null)
    val startDestination: StateFlow<LoginOrHome?> = _startDestination

    init {
        viewModelScope.launch {
            val autoLogin = sharedPrefs.getAutologin()
            _startDestination.value = if (autoLogin) LoginOrHome.Home else LoginOrHome.Login
        }
    }
}

sealed class LoginOrHome {
    object Login : LoginOrHome()
    object Home : LoginOrHome()
}