package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.common.PSScaffold
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
                    sheltersList = uiState.value.sheltersList,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun SheltersDetailBody(
    shelter: Shelter?,
    sheltersList: List<Shelter>,
    modifier: Modifier = Modifier
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
                text = "Otros refugios",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            ShelterItems(
                shelters = sheltersList,
                navigateToShelterDetail = {}
            )
        }
    }
}


@Composable
private fun ShelterItems(
    shelters: List<Shelter>,
    navigateToShelterDetail: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(shelters) { shelter ->
            HomeItem(
                nombre = shelter.nombre,
                descripcion = shelter.direccion,
                imagenRes = shelter.imagenRes,
                modifier = Modifier.clickable { navigateToShelterDetail(shelter.id) }
            )
        }
    }
}