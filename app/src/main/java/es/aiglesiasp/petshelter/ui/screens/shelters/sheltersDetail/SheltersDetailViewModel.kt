package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.domain.repositories.ShelterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheltersDetailViewModel @Inject constructor(
    private val sheltersRepository: ShelterRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    data class UiState(
        val isLoading: Boolean = true,
        val shelter: Shelter? = null,
        val sheltersList: List<Shelter> = emptyList()
    )

    fun loadData(shelterId: Int) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val shelter = sheltersRepository.getShelterById(shelterId)
            val shelters = sheltersRepository.getShelters()
            val sheltersFiltered = shelters.filter { it.id != shelter?.id }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                shelter = shelter,
                sheltersList = sheltersFiltered
            )
        }
    }
}