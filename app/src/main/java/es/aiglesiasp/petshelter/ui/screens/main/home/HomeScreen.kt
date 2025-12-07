package es.aiglesiasp.petshelter.ui.screens.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
import es.aiglesiasp.petshelter.ui.navigation.PetList
import es.aiglesiasp.petshelter.ui.navigation.ShelterDetail
import es.aiglesiasp.petshelter.ui.navigation.ShelterList

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
                navigateToPetList = { navController.navigate(PetList) },
                navigateToShelterList = { navController.navigate(ShelterList) },
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
    navigateToPetList: () -> Unit,
    navigateToShelterList: () -> Unit,
    navigateToPetDetail: (Int) -> Unit,
    navigateToShelterDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        item { PetTitle(navigateToPetList) }
        item { PetItems(pets, navigateToPetDetail) }
        item { ShelterTitle(navigateToShelterList) }
        item { ShelterItems(shelters, navigateToShelterDetail) }
    }
}

@Composable
private fun PetTitle(navigateToPetList: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mascotas Disponibles",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )

        ArrowForwardIcon(navigateToPetList)
    }
}

@Composable
fun PetItems(
    pets: List<Pet>,
    navigateToPetDetail: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.height(265.dp),   // ⬅️ altura fija de la fila
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

@Composable
private fun ShelterTitle(navigateToShelterList: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Refugios Disponibles",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
        ArrowForwardIcon(navigateToShelterList)
    }
}


@Composable
private fun ShelterItems(
    shelters: List<Shelter>,
    navigateToShelterDetail: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.height(265.dp),   // ⬅️ misma altura
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(shelters) { shelter ->
            HomeItem(
                nombre = shelter.nombre,
                descripcion = shelter.direccion,
                imagenRes = shelter.imagenRes,
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { navigateToShelterDetail(shelter.id) }
            )
        }
    }
}

@Composable
private fun ArrowForwardIcon(onIconClick: () -> Unit) {
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = "Go",
        modifier = Modifier
            .size(32.dp)
            .clickable { onIconClick() }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeBody_Preview() {
    val samplePets = listOf(
        Pet(
            id = 0,
            nombre = "Buddy",
            raza = "Golden Retriever",
            tipo = "Perro",
            edad = "2 años",
            descripcion = "Un perro cariñoso y leal.",
            imagenRes = "perro1",
            refugio = "Refugio 1",
            isFavorite = false
        ),
        Pet(
            id = 1,
            nombre = "Whiskers",
            raza = "Whiskers",
            tipo = "Perro",
            edad = "10 meses",
            descripcion = "Un gato cariñoso y leal.",
            imagenRes = "perro2",
            refugio = "Refugio 2",
            isFavorite = false
        ),
        Pet(
            id = 2,
            nombre = "Max",
            raza = "German Shepherd",
            tipo = "Perro",
            edad = "3 años",
            descripcion = "Un perro grande.",
            imagenRes = "https://picsum.photos/seed/dog1/600/600",
            refugio = "Refugio 3",
            isFavorite = false
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
        navigateToPetList = {},
        navigateToShelterList = {},
        navigateToPetDetail = {},
        navigateToShelterDetail = {}
    )
}