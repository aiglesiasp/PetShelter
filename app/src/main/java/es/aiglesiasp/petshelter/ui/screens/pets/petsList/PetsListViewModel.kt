package es.aiglesiasp.petshelter.ui.screens.pets.petsList

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
class PetsListViewModel@Inject constructor(
    private val petsRepository: PetsRepository
): ViewModel()  {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState

    var petsResult: List<Pet> = emptyList()

    data class UiState(
        val isLoading: Boolean = true,
        val pets: List<Pet> = emptyList(),
        val searchQuery: String = "",
        val selectedFilter: Filter = Filter.ALL
    )

    init {
        getPets()
    }

    private fun getPets() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val pets = petsRepository.getPets()
            petsResult = pets
            _uiState.value = _uiState.value.copy(isLoading = false, pets = pets)
        }
    }

    fun onSearchClick(newSearchQuery: String) {
        _uiState.value = _uiState.value.copy(searchQuery = newSearchQuery)
        if(newSearchQuery.isEmpty()) {
            _uiState.value = _uiState.value.copy(pets = petsResult)
        } else {
            val petsFilter = petsResult.filter { pet ->
                pet.nombre.contains(newSearchQuery) || pet.raza.contains(newSearchQuery)
            }
            _uiState.value = _uiState.value.copy(pets = petsFilter)
        }
    }

    fun onFilterClick(filter: Filter) {
        _uiState.value = _uiState.value.copy(pets = filterPetsListByFilter(filter), selectedFilter = filter)
    }

    private fun filterPetsListByFilter(filter: Filter): List<Pet> {
        return if(filter == Filter.ALL) {
            petsResult
        } else {
            petsResult.filter { pet ->
                pet.tipoAnimal == filter
            }
        }
    }
}