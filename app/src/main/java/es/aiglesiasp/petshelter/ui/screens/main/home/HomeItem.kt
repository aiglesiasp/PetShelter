package es.aiglesiasp.petshelter.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.aiglesiasp.petshelter.R


@Composable
fun HomeItem(
    nombre: String,
    descripcion: String,
    imagenRes: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val imageResId = remember(imagenRes) {
        context.resources.getIdentifier(
            imagenRes,
            "drawable",
            context.packageName
        )
    }

    Card(
        modifier = modifier.width(220.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            val finalResId = if (imageResId != 0) {
                imageResId
            } else {
                // fallback para evitar el crash si no lo encuentra
                R.drawable.ic_launcher_background // o un placeholder si quieres
            }

            Image(
                painter = painterResource(id = finalResId),
                contentDescription = nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = nombre,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeItemPreview() {
    HomeItem(
        nombre = "NOMBRE",
        descripcion = "RAZA, EDAD",
        imagenRes = "perro1",
    )
}