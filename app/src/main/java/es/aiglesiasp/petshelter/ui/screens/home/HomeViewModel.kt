package es.aiglesiasp.petshelter.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val petsRepository: PetsRepository,
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val pets: List<Pet> = emptyList(),
        val shelters: List<Shelter> = emptyList(),
    )

    init {
        getAllPets()
        //getAllShelters
    }

    private fun getAllPets() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch {
            val pets = petsRepository.getPets()
            _uiState.value = UiState(isLoading = false, pets = pets)
        }

    }
}