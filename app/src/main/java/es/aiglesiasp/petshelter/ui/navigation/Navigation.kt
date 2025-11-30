package es.aiglesiasp.petshelter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.aiglesiasp.petshelter.ui.screens.home.HomeScreen
import es.aiglesiasp.petshelter.ui.screens.pets.petsDetail.PetsDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                navigateToPetDetail = { petId ->
                    navController.navigate(PetDetail(petId = petId))
                },
                navigateToShelterDetail = { shelterId ->
                    navController.navigate(ShelterDetail(shelterId = shelterId))
                }
            )
        }

        composable<PetDetail>() { backStackEntry ->
            val petId = backStackEntry.toRoute<PetDetail>()
            PetsDetailScreen(
                petId = petId.petId,
                navigateToBack = { navController.popBackStack() }
            )
        }

        composable<ShelterDetail>() { backStackEntry ->
            val shelterId = backStackEntry.toRoute<ShelterDetail>()

        }
    }
}