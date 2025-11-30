package es.aiglesiasp.petshelter.ui.screens.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.PetDetail
import es.aiglesiasp.petshelter.ui.navigation.ShelterDetail

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsState()

    ScreenAppTheme {
        PSScaffold(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
        ) { paddingValues ->
            HomeBody(
                pets = uiState.value.pets,
                shelters = uiState.value.shelters,
                modifier = Modifier.padding(paddingValues),
                navigateToPetDetail = { petId ->
                    navController.navigate(PetDetail(petId = petId))
                },
                navigateToShelterDetail = { shelterId ->
                    navController.navigate(ShelterDetail(shelterId = shelterId))
                }
            )
        }
    }
}

@Composable
fun HomeBody(
    pets: List<Pet>,
    shelters: List<Shelter>,
    modifier: Modifier = Modifier,
    navigateToPetDetail: (Int) -> Unit,
    navigateToShelterDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 96.dp
        )
    ) {

        item {
            Text(
                text = "Available Pets",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pets) { pet ->
                    HomeItem(
                        title = pet.nombre,
                        subTitle = "${pet.raza},${pet.edad} años",
                        imagenRes = pet.imagenRes,
                        modifier = Modifier.clickable{ navigateToPetDetail(pet.id)}
                    )
                }
            }
        }

        item {
            Text(
                text = "Featured Shelters",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(shelters) { shelter ->
                    HomeItem(
                        title = shelter.nombre,
                        subTitle = shelter.direccion,
                        imagenRes = shelter.imagenRes,
                        modifier = Modifier.clickable{ navigateToShelterDetail(shelter.id)}
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeBody_Preview() {
    val samplePets = listOf(
        Pet(
            id = 0,
            nombre = "Buddy",
            raza = "Golden Retriever",
            edad = 2,
            descripcion = "Un perro cariñoso y leal.",
            imagenRes = "perro1",
            refugioId = 0
        ),
        Pet(
            id = 1,
            nombre = "Whiskers",
            raza = "Whiskers",
            edad = 1,
            descripcion = "Un gato cariñoso y leal.",
            imagenRes = "perro2",
            refugioId = 0
        ),
        Pet(
            id = 2,
            nombre = "Max",
            raza = "German Shepherd",
            edad = 3,
            descripcion = "Un perro grande.",
            imagenRes = "https://picsum.photos/seed/dog1/600/600",
            refugioId = 0
        )
    )

    val sampleShelters = listOf(
        Shelter(
            id = 0,
            nombre = "Happy Tails Shelter",
            direccion = "123 Main St, Anytown",
            telefono = "123-456-7890",
            email = "",
            imagenRes = "refugio1"
        ),
        Shelter(
            id = 0,
            nombre = "\"Second Chance Rescue\"",
            direccion = "456 Oak Ave, Springfield",
            telefono = "123-456-7890",
            email = "",
            imagenRes = "https://picsum.photos/seed/shelter2/600/600"
        )
    )

    HomeBody(
        pets = samplePets,
        shelters = sampleShelters,
        navigateToPetDetail = {},
        navigateToShelterDetail = {}
    )
}