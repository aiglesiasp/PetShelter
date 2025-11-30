package es.aiglesiasp.petshelter.ui.screens.pets.petsDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold

@Composable
fun PetsDetailScreen(
    petId: Int,
    navigateToBack: () -> Unit
) {
    PetsDetailContent(
        pet = Pet(
            id = 1,
            nombre = "Luna",
            raza = "Mestiza",
            edad = 2,
            descripcion = "Perra muy cariñosa y juguetona, ideal con familias.",
            imagenRes = "perro1",
            refugioId = 1
        ),
        navigateToBack = navigateToBack
    )
}

@Composable
private fun PetsDetailContent(
    pet: Pet,
    navigateToBack: () -> Unit
) {
    ScreenAppTheme {
        PSScaffold(
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
            showArrowBack = true,
            onArrowBackClick = navigateToBack
        ) { paddingValues ->
            PetsDetailBody(
                pet = pet,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun PetsDetailBody(
    pet: Pet,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val imageResId = remember(pet.imagenRes) {
        context.resources.getIdentifier(
            pet.imagenRes,
            "drawable",
            context.packageName
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = pet.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Text(pet.nombre)
        Text(pet.raza)
        Text(pet.edad.toString())
        Text(pet.descripcion)

    }

}