package by.eapp.mts.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.eapp.mts.presentation.ui.History.HistoryScreen
import by.eapp.mts.presentation.ui.Home.SpeechToTextViewModel
import by.eapp.mts.presentation.ui.Home.TranslateSpeechScreen
import by.eapp.mts.presentation.ui.Contacts.ContactsScreen
import by.eapp.mts.presentation.ui.Contacts.ContactsViewModel
import by.eapp.mts.presentation.ui.Operations.OperationsScreen
import by.eapp.mts.presentation.ui.Profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    speechToTextViewModel: SpeechToTextViewModel = hiltViewModel(),
    contactsViewModel: ContactsViewModel = hiltViewModel(),
) {
    NavHost(navController = navController, startDestination = Screens.Home.route, modifier = modifier) {
        composable(route = Screens.Home.route) {
            TranslateSpeechScreen(
                navController = navController,
                viewModel = speechToTextViewModel
            )
        }
        composable(route = Screens.Operations.route) {
            OperationsScreen(navHostController = navController)
        }
        composable(route = Screens.History.route) {
            HistoryScreen(navHostController = navController)
        }
        composable(route = Screens.MyContacts.route) {
            ContactsScreen(
                navHostController = navController,
                viewModel = contactsViewModel
            )
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(navHostController = navController)
        }
    }
}