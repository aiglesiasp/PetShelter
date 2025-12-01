package es.aiglesiasp.petshelter.domain.model

data class Pet(
    val id: Int,
    val nombre: String,
    val raza: String,
    val edad: String,
    val descripcion: String,
    val imagenRes: String,
    val refugio: String
)