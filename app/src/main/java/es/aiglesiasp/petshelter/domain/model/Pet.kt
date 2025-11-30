package es.aiglesiasp.petshelter.domain.model

data class Pet(
    val id: Int,
    val nombre: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val imagenUrl: String,
    val refugioId: Int
)