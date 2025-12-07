package es.aiglesiasp.petshelter.ui.screens.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val petsRepository: PetsRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val pets: List<Pet> = emptyList()
    )

    init {
        getAllFavoritesPets()
    }

    private fun getAllFavoritesPets() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = petsRepository.getAllFavoritesPets()
            _uiState.value = _uiState.value.copy(isLoading = false, pets = result)
        }
    }
}