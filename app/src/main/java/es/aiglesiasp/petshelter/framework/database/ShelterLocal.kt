package es.aiglesiasp.petshelter.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.aiglesiasp.petshelter.domain.model.Shelter
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "shelters")
data class ShelterLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val direccion: String,
    val telefono: String,
    val email: String,
    val imagenRes: String
)

fun ShelterLocal.toDomain(): Shelter {
    return Shelter(
        id = id,
        nombre = nombre,
        direccion = direccion,
        telefono = telefono,
        email = email,
        imagenRes = imagenRes
    )
}