package es.aiglesiasp.petshelter.ui.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository
import es.aiglesiasp.petshelter.domain.repositories.ShelterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val petsRepository: PetsRepository,
    private val sheltersRepository: ShelterRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = false,
        val pets: List<Pet> = emptyList(),
        val shelters: List<Shelter> = emptyList(),
    )

    init {
        getAllData()
    }

    private fun getAllData() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch {
            val pets = petsRepository.getPets()
            val shelters = sheltersRepository.getShelters()
            _uiState.value = _uiState.value.copy(isLoading = false, pets= pets, shelters= shelters)
        }
    }
}