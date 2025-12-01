package es.aiglesiasp.petshelter.ui.screens.pets.petsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold

@Composable
fun PetsListScreen(
    navController: NavController,
    vm: PetsListViewModel = hiltViewModel(),
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
                // Buscador
                item {
                    SearchItem(
                        searchValue = uiState.value.searchQuery,
                        onSearchClick = { newValue ->
                            vm.onSearchClick(newValue)
                        }
                    )
                }

                // Chips de filtro
                item {
                    FiltersChips(
                        selected = uiState.value.selectedFilter,
                        onFilterClick = { filter ->
                            vm.onFilterClick(filter)
                        }
                    )
                }

                // Título de sección
                item {
                    Text(
                        text = "Animales cerca de ti",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Lista de animales
                items(uiState.value.pets) { pet ->
                    PetsListItem(
                        modifier = Modifier.padding(top = 8.dp),
                        pet = pet
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchItem(searchValue: String, onSearchClick: (String) -> Unit) {
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
            Text(text = "Buscar por nombre, raza…")
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

@Composable
private fun FiltersChips(
    selected: Filter,
    onFilterClick: (Filter) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 8.dp)
    ) {
        FilterChip(
            selected = selected == Filter.ALL,
            onClick = { onFilterClick(Filter.ALL) },
            label = {
                Text(Filter.ALL.value)
            }
        )

        FilterChip(
            selected = selected == Filter.DOGS,
            onClick = { onFilterClick(Filter.DOGS) },
            label = {
                Text(Filter.DOGS.value)
            }
        )

        FilterChip(
            selected = selected == Filter.CATS,
            onClick = { onFilterClick(Filter.CATS) },
            label = {
                Text(Filter.CATS.value)
            }
        )

        FilterChip(
            selected = selected == Filter.OTHERS,
            onClick = { onFilterClick(Filter.OTHERS) },
            label = {
                Text(Filter.OTHERS.value)
            }
        )
    }
}

enum class Filter(val value: String) {
    ALL("Todos"), DOGS("Perros"), CATS("Gatos"), OTHERS("Otros")
}

@Preview
@Composable
fun PetsListScreenPreview() {
    PetsListScreen(navController = NavController(LocalContext.current))
}