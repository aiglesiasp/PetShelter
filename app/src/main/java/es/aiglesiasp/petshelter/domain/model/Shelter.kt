package es.aiglesiasp.petshelter.domain.model

data class Shelter(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val imagenRes: String
)