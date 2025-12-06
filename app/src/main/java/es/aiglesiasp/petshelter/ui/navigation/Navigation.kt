package es.aiglesiasp.petshelter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.screens.login.LoginScreen
import es.aiglesiasp.petshelter.ui.screens.main.favorite.FavoriteScreen
import es.aiglesiasp.petshelter.ui.screens.main.home.HomeScreen
import es.aiglesiasp.petshelter.ui.screens.main.profile.ProfileScreen
import es.aiglesiasp.petshelter.ui.screens.pets.petsDetail.PetsDetailScreen
import es.aiglesiasp.petshelter.ui.screens.pets.petsList.PetsListScreen
import es.aiglesiasp.petshelter.ui.screens.register.RegisterScreen
import es.aiglesiasp.petshelter.ui.screens.shelters.sheltersDetail.SheltersDetailScreen
import es.aiglesiasp.petshelter.ui.screens.shelters.sheltersList.SheltersListScreen

@Composable
fun Navigation(
    viewModel: NavigationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val startDestinationState = viewModel.startDestination.collectAsState()

    if (startDestinationState.value == null) {
        return LoadingProgressIndicator()
    }

    val startDestination = when (startDestinationState.value) {
        LoginOrHome.Login -> Login
        LoginOrHome.Home -> Home
        else -> Login // fallback
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Login> {
            LoginScreen(navController = navController)
        }

        composable<Register> {
            RegisterScreen(navController = navController)
        }

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