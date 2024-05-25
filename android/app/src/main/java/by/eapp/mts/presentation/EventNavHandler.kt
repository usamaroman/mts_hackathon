package by.eapp.mts.presentation

import androidx.navigation.NavController
import by.eapp.mts.presentation.navigation.Screens
import java.util.Locale

class EventNavHandler(
    private val navController: NavController
) {

    private val keywords = mapOf(
        "контакты" to "contacts",
        "операция" to "operation",
        "профиль" to "profile",
        "история" to "history",
        "домой" to "home",
    )
    fun handleEvent(speech: String) {
        val keyword = keywords[speech.lowercase(Locale.ROOT)]
        keyword?.let {
            navigateTo(it)
        }
    }
    fun navigateTo(destination:String) {
        when (destination) {
            "contacts" -> {
               navController.navigate(Screens.MyContacts.route)
            }
            "operation" -> {
                navController.navigate(Screens.Operations.route)
            }
            "profile" -> {
                navController.navigate(Screens.Profile.route)
            }
            "history" -> {
                navController.navigate(Screens.History.route)
            }
            "home" -> {
                navController.navigate(Screens.Home.route)
            }
        }
    }

}