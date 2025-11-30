package es.aiglesiasp.petshelter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object Home

@Serializable
object PetList

@Serializable
object ShelterList

@Serializable
data class PetDetail(val petId: Int)

@Serializable
data class ShelterDetail(val shelterId: Int)

@Serializable
object Profile