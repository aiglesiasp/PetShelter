package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersList

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
class SheltersListViewModel @Inject constructor(
    private val sheltersRepository: ShelterRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    var sheltersResult: List<Shelter> = emptyList()

    data class UiState(
        val isLoading: Boolean = true,
        val shelters: List<Shelter> = emptyList(),
        val searchQuery: String = ""
    )

    init {
        getShelters()
    }

    private fun getShelters() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val shelters = sheltersRepository.getShelters()
            sheltersResult = shelters
            _uiState.value = _uiState.value.copy(isLoading = false, shelters = shelters)
        }
    }

    fun onSearchClick(newSearchQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newSearchQuery)
        if(newSearchQuery.isEmpty()) {
            _uiState.value = _uiState.value.copy(shelters = sheltersResult)
        } else {
            val sheltersFilters = sheltersResult.filter { shelter ->
                shelter.nombre.contains(newSearchQuery)
            }
            _uiState.value = _uiState.value.copy(shelters = sheltersFilters)
        }
    }
}