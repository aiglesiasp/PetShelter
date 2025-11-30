package es.aiglesiasp.petshelter.framework

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import es.aiglesiasp.petshelter.domain.Pet
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "pets",
    foreignKeys = [
        ForeignKey(
            entity = ShelterLocal::class,
            parentColumns = ["id"],
            childColumns = ["refugioId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("refugioId")]
)
data class PetLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nombre: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val imagenUrl: String,

    // Relación N:1 → cada animal pertenece a un refugio
    val refugioId: Int
)

fun PetLocal.toDomain(): Pet {
    return Pet(
        id = id,
        nombre = nombre,
        raza = raza,
        edad = edad,
        descripcion = descripcion,
        imagenUrl = imagenUrl,
        refugioId = refugioId
    )
}