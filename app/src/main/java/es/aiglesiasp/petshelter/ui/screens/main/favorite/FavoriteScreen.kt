package es.aiglesiasp.petshelter.ui.screens.main.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.PetDetail
import es.aiglesiasp.petshelter.ui.screens.pets.petsList.PetsListItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.collectAsState()

    ScreenAppTheme {
        PSScaffold(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
        ) { paddingValues ->
            if (uiState.value.pets.isNotEmpty()) {
                FavoriteBody(
                    pets = uiState.value.pets,
                    modifier = Modifier.padding(paddingValues),
                    navigateToPetDetail = { petId ->
                        navController.navigate(PetDetail(petId = petId))
                    }
                )
            } else {
                EmptyFavoriteView(paddingValues)
            }
        }
    }
}

@Composable
private fun FavoriteBody(
    pets: List<Pet>,
    modifier: Modifier = Modifier,
    navigateToPetDetail: (Int) -> Unit,
    ) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(pets) { pet ->
            PetsListItem(
                modifier = Modifier.padding(top = 8.dp),
                pet = pet,
                onPetClick = { petId ->
                    navigateToPetDetail(petId)
                }
            )
        }
    }
}

@Composable
private fun EmptyFavoriteView(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Aún no ha seleccionado ningun favorito",
            style = MaterialTheme.typography.titleLarge
        )
    }
}