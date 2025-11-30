package es.aiglesiasp.petshelter.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.aiglesiasp.petshelter.domain.Pet
import es.aiglesiasp.petshelter.domain.Shelter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {

    val pets: List<Pet> = emptyList()
    val selectedPet: Pet? = null
    val shelters: List<Shelter> = emptyList()
}