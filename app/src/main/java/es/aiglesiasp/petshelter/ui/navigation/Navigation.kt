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

        composable<PetDetail>() { backStackEntry ->
            val petId = backStackEntry.toRoute<PetDetail>()
            PetsDetailScreen(
                navController = navController,
                petId = petId.petId
            )
        }

        composable<ShelterDetail>() { backStackEntry ->
            val shelterId = backStackEntry.toRoute<ShelterDetail>()
        }
    }
}