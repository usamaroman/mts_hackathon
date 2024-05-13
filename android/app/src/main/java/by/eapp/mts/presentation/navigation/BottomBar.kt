package by.eapp.mts.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.eapp.mts.ui.theme.NavBar
import by.eapp.mts.ui.theme.SelectedNavBarItem

@Composable
fun BottomBar(
    navController: NavHostController,
) {

    val items = listOf(
        Screens.History,
        Screens.Profile,
        Screens.Home,
        Screens.MyContacts,
        Screens.Operations
    )

    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
        BottomNavigation(
            backgroundColor = NavBar
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        val iconTint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                            SelectedNavBarItem
                        } else {
                            Color.DarkGray
                        }
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.route,
                            tint = iconTint
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.semantics {
                        contentDescription = screen.route
                    }
                )
            }
        }
    }
}
