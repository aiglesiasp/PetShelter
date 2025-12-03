package es.aiglesiasp.petshelter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.aiglesiasp.petshelter.ui.screens.main.favorite.FavoriteScreen
import es.aiglesiasp.petshelter.ui.screens.main.home.HomeScreen
import es.aiglesiasp.petshelter.ui.screens.main.profile.ProfileScreen
import es.aiglesiasp.petshelter.ui.screens.pets.petsDetail.PetsDetailScreen
import es.aiglesiasp.petshelter.ui.screens.pets.petsList.PetsListScreen
import es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail.SheltersDetailScreen
import es.aiglesiasp.petshelter.ui.screens.shelters.sheltersList.SheltersListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(navController = navController)
        }

        composable<Favorite> {
            FavoriteScreen(navController = navController)
        }

        composable<Profile> {
            ProfileScreen(navController = navController)
        }

        composable<PetList> {
            PetsListScreen(navController = navController)
        }

        composable<PetDetail>() { backStackEntry ->
            val petId = backStackEntry.toRoute<PetDetail>()
            PetsDetailScreen(
                navController = navController,
                petId = petId.petId
            )
        }

        composable<ShelterList> {
            SheltersListScreen(navController = navController)
        }

        composable<ShelterDetail>() { backStackEntry ->
            val shelterId = backStackEntry.toRoute<ShelterDetail>()
            SheltersDetailScreen(
                navController = navController,
                shelterId = shelterId.shelterId
            )
        }
    }
}