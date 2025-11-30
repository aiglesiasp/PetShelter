package es.aiglesiasp.petshelter.framework.database

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
data class ShelterWithPets(
    @Embedded val shelter: ShelterLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "refugioId"
    )
    val pets: List<PetLocal>
)