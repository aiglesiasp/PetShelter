package es.aiglesiasp.petshelter.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.aiglesiasp.petshelter.domain.model.Pet
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pets")
data class PetLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val raza: String,
    val tipo: String,
    val edad: String,
    val descripcion: String,
    val imagenRes: String,
    val refugio: String
)

fun PetLocal.toDomain(): Pet {
    return Pet(
        id = id,
        nombre = nombre,
        raza = raza,
        tipo = tipo,
        edad = edad,
        descripcion = descripcion,
        imagenRes = imagenRes,
        refugio = refugio
    )
}