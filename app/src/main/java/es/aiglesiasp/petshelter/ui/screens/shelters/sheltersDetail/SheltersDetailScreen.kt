package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.domain.model.Shelter
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.common.PSScaffold

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
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun SheltersDetailBody(
    shelter: Shelter?,
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = finalResId),
            contentDescription = shelter?.nombre,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        shelter?.nombre?.let { Text(it) }
        shelter?.direccion?.let { Text(it) }
        shelter?.telefono?.let { Text(it) }
        shelter?.email?.let { Text(it) }

    }

}