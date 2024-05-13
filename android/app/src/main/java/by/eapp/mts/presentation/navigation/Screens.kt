package by.eapp.mts.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val icon: ImageVector,
    val title: String,
) {

    data object Profile : Screens(
        route = "profile",
        icon = Icons.Default.AccountCircle,
        title = "Profile"
    )
    data object Operations : Screens(
        route = "operations",
        icon = Icons.Default.Add,
        title = "Operations"
    )
    data object History : Screens(
        route = "history",
        icon = Icons.Default.List,
        title = "History"
    )
    data object Home : Screens(
        route = "home",
        icon = Icons.Default.Home,
        title = "Home"
    )
    data object MyContacts : Screens(
        route = "contacts",
        icon = Icons.Default.Call,
        title = "My Contacts"
    )
}