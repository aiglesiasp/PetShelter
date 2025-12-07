package es.aiglesiasp.petshelter.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import es.aiglesiasp.petshelter.ui.navigation.Home

@Composable
fun PSBottomAppBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorites,
        BottomNavItem.Profile
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val currentRoute = currentDestination?.route

        items.forEach { item ->

            val selected = currentRoute == item.navRoute

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        popUpTo(Home) { inclusive = false }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}

sealed class BottomNavItem(
    val route: Any,
    val title: String,
    val icon: ImageVector
) {
    val navRoute: String
        get() = route::class.qualifiedName ?: ""


    object Home : BottomNavItem(
        route = es.aiglesiasp.petshelter.ui.navigation.Home,
        title = "Inicio",
        icon = Icons.Default.Home
    )

    object Favorites : BottomNavItem(
        route = es.aiglesiasp.petshelter.ui.navigation.Favorite,
        title = "Favoritos",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomNavItem(
        route = es.aiglesiasp.petshelter.ui.navigation.Profile,
        title = "Perfil",
        icon = Icons.Default.Person
    )
}