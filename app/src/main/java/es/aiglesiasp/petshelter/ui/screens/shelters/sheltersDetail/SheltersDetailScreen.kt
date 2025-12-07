package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Pet
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.PetDetail
import es.aiglesiasp.petshelter.ui.screens.main.home.HomeItem

@Composable
fun SheltersDetailScreen(
    navController: NavController,
    vm: SheltersDetailViewModel = hiltViewModel(),
    shelterId: Int
) {

    LaunchedEffect(Unit) {
        vm.loadData(shelterId)
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
                SheltersDetailBody(
                    shelter = uiState.value.shelter,
                    petList = uiState.value.petList,
                    modifier = Modifier.padding(paddingValues),
                    navigateToPetDetail = { petId ->
                        navController.navigate(PetDetail(petId = petId))
                    }
                )
            }
        }
    }
}

@Composable
private fun SheltersDetailBody(
    shelter: Shelter?,
    petList: List<Pet>,
    modifier: Modifier = Modifier,
    navigateToPetDetail: (Int) -> Unit
) {
    val context = LocalContext.current

    val imageResId = context.resources.getIdentifier(
        shelter?.imagenRes,
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
    )  {
        item {
            Image(
                painter = painterResource(id = finalResId),
                contentDescription = shelter?.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        item {
            shelter?.nombre?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        item { shelter?.direccion?.let { Text(it) } }
        item { shelter?.telefono?.let { Text(it) } }
        item { shelter?.email?.let { Text(it) } }
        item {
            Text(
                text = "Animales",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            PetItems(
                pets = petList,
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