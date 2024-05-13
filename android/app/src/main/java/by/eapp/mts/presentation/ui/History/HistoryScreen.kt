package by.eapp.mts.presentation.ui.History

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.eapp.mts.presentation.navigation.BottomBar

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) },
        containerColor = Color.White
    ) {
        it

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "History", fontSize = 25.sp, color = Color.Black)
        }
    }
}