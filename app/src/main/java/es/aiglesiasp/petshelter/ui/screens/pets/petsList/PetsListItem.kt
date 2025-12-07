package es.aiglesiasp.petshelter.ui.screens.pets.petsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet

@Composable
fun PetsListItem(
    pet: Pet,
    modifier: Modifier = Modifier,
    onPetClick: (Int) -> Unit
) {
    val context = LocalContext.current

    val imageResId = remember(pet.imagenRes) {
        context.resources.getIdentifier(
            pet.imagenRes,
            "drawable",
            context.packageName
        )
    }

    val finalResId = if (imageResId != 0) {
        imageResId
    } else {
        // fallback para evitar el crash si no lo encuentra
        R.drawable.ic_launcher_background // o un placeholder si quieres
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable{ onPetClick(pet.id) },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = pet.nombre,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = pet.edad,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = pet.raza,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = pet.refugio,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = finalResId),
                contentDescription = pet.nombre,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(140.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PetsListItemPreview() {

    val mockPet = Pet(
        id = 0,
        nombre = "Max",
        raza = "Labrador Retriever",
        tipo = "Perro",
        edad = "2 años",
        descripcion = "",
        imagenRes = "perro1",
        refugio = "Refugio 1",
    )
    PetsListItem(
        pet = mockPet,
        onPetClick = {}
    )
}