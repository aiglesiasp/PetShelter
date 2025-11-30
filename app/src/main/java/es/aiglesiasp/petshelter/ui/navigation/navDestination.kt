package es.aiglesiasp.petshelter.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login
@Serializable
object Register

@Serializable
object Home
@Serializable
object Favorite
@Serializable
object Profile

@Serializable
object PetList
@Serializable
data class PetDetail(val petId: Int)

@Serializable
object ShelterList
@Serializable
data class ShelterDetail(val shelterId: Int)