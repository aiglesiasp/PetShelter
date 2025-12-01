package es.aiglesiasp.petshelter.domain.model

import es.aiglesiasp.petshelter.ui.screens.pets.petsList.Filter

data class Pet(
    val id: Int,
    val nombre: String,
    val raza: String,
    val tipo: String,
    val edad: String,
    val descripcion: String,
    val imagenRes: String,
    val refugio: String
) {
    val tipoAnimal: Filter
        get() = when (tipo) {
            "Perro" -> Filter.DOGS
            "Gato" -> Filter.CATS
            else -> Filter.OTHERS
        }
}
