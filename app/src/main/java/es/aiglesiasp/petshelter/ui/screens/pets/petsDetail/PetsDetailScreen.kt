package es.aiglesiasp.petshelter.ui.screens.pets.petsDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.PetDetail
import es.aiglesiasp.petshelter.ui.screens.main.home.HomeItem

@Composable
fun PetsDetailScreen(
    navController: NavController,
    vm: PetsDetailViewModel = hiltViewModel(),
    petId: Int,
) {

    LaunchedEffect(Unit) {
        vm.loadData(petId)
    }

    val uiState = vm.uiState.collectAsState()

    ScreenAppTheme {
        PSScaffold(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
            showTopAppBarArrowBack = true
        ) { paddingValues ->
            if (uiState.value.isLoading) {
                LoadingProgressIndicator()
            } else {
                PetsDetailBody(
                    pet = uiState.value.pet,
                    petsList = uiState.value.petsList,
                    modifier = Modifier.padding(paddingValues),
                    navigateToPetDetail = { petId ->
                        navController.navigate(PetDetail(petId = petId))
                    },
                    onFavoriteClick = { petId ->
                        vm.onToggleFavoriteClick(petId)
                    }
                )
            }
        }
    }
}

@Composable
private fun PetsDetailBody(
    pet: Pet?,
    petsList: List<Pet>,
    modifier: Modifier = Modifier,
    navigateToPetDetail: (Int) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    val context = LocalContext.current

    val imageResId = context.resources.getIdentifier(
        pet?.imagenRes,
        "drawable",
        context.packageName
    )

    val finalResId = if (imageResId != 0) {
        imageResId
    } else {
        R.drawable.ic_launcher_background
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            Image(
                painter = painterResource(id = finalResId),
                contentDescription = pet?.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                pet?.nombre?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Icon(
                        imageVector = if (pet.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Seleccione un rol",
                        modifier = Modifier.clickable {
                            onFavoriteClick(pet.id)
                        }
                    )
                }


            }
        }

        item {
            pet?.descripcion?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        item {
            Text(
                text = "Otros animales",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            PetItems(
                pets = petsList,
                navigateToPetDetail = navigateToPetDetail
            )
        }
    }
}

@Composable
private fun PetItems(
    pets: List<Pet>,
    navigateToPetDetail: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.height(265.dp),   // ⬅️ misma altura
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pets) { pet ->
            HomeItem(
                nombre = pet.nombre,
                descripcion = "${pet.raza}, ${pet.edad}",
                imagenRes = pet.imagenRes,
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { navigateToPetDetail(pet.id) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PetsDetailBody_Preview() {
    val pet = Pet(
        id = 1,
        nombre = "Max",
        raza = "Labrador Retriever",
        tipo = "Perro",
        edad = "3 años",
        imagenRes = "perro1",
        descripcion = "Es un perro muy bonito",
        refugio = "Refugio 1",
        isFavorite = false
    )
    
    val petsList = listOf(
        pet
    )

    PetsDetailBody(
        pet = pet,
        petsList = petsList,
        navigateToPetDetail = {},
        onFavoriteClick = {}
    )
}