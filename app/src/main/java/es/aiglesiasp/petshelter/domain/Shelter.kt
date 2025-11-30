package es.aiglesiasp.petshelter.domain

data class Shelter(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val imagenUrl: String
)