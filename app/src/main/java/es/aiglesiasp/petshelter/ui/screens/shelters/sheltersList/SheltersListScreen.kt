package es.aiglesiasp.petshelter.ui.screens.shelters.sheltersList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.ShelterDetail

@Composable
fun SheltersListScreen(
    navController: NavController,
    vm: SheltersListViewModel = hiltViewModel()
) {
    val uiState = vm.uiState.collectAsState()

    ScreenAppTheme {
        PSScaffold(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
            showArrowBack = true
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    SearchShelterItem(
                        searchValue = uiState.value.searchQuery,
                        onSearchClick = { newValue ->
                            vm.onSearchClick(newValue)
                        }
                    )
                }

                item {
                    Text(
                        text = "Refugios cerca de ti",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Lista de animales
                items(uiState.value.shelters) { shelter ->
                    SheltersListItem(
                        modifier = Modifier.padding(top = 8.dp),
                        shelter = shelter,
                        onShelterClick = { shelterId ->
                            navController.navigate(ShelterDetail(shelterId))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchShelterItem(searchValue: String, onSearchClick: (String) -> Unit) {
    OutlinedTextField(
        value = searchValue,
        onValueChange = { value ->
            onSearchClick(value)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar"
            )
        },
        placeholder = {
            Text(text = "Buscar por nombre...")
        },
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    )
}