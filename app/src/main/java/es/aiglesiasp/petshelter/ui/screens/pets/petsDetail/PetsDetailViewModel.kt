package es.aiglesiasp.petshelter.ui.screens.pets.petsDetail

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
class PetsDetailViewModel @Inject constructor(
    private val petsRepository: PetsRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = true,
        val pet: Pet? = null,
        val petsList: List<Pet> = emptyList()
    )

    fun loadData(petId: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val pet = petsRepository.getPetById(petId)
            val pets = petsRepository.getPets()
            val petsFiltered = pets.filter { it.id != pet?.id }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                pet = pet,
                petsList = petsFiltered
            )
        }
    }
}