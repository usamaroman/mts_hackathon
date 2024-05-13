package by.eapp.mts.presentation.ui.Operations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import by.eapp.mts.domain.model.Operation
import by.eapp.mts.presentation.navigation.BottomBar
import by.eapp.mts.presentation.utils.CustomText
import by.eapp.mts.ui.theme.IncomeColor
import by.eapp.mts.ui.theme.OutcomeColor

@Composable
fun OperationsScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val operationsList = listOf(
        Operation("Anastasia Novikova", "21.12.2024 18:49", 43, true),
        Operation("Sasha Fokin", "21.12.2024 18:49", 123, false),
        Operation("Diana Kononovich", "21.12.2024 18:49", 543, true),
        Operation("Roma Chechyotkin", "21.12.2024 18:49", 555, true),
        Operation("Robert Gaspyaran", "21.12.2024 18:49", 10, false),
        Operation("John Doe", "21.12.2024 18:49", 99, true)
    )

    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) },
        containerColor = Color.White
    ) {
        LazyColumn(
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            item {
                BalanceCard(balance = 452.6)
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(operationsList) { operation ->
                OperationCard(
                    contact = operation.contact,
                    time = operation.time,
                    amount = operation.amount,
                    income = operation.income
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun BalanceCard(
    balance: Double
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = CardDefaults.shape

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = balance.toString(),
                    fontSize = 42,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(12.dp))
                CustomText(
                    text = "BYN",
                    fontSize = 16
                )
            }

        }
    }
}
// короче дядь
// income это когда теье деньги пришли
// если тру, значит плюсик и фон зеленый
@Composable
fun OperationCard(
    contact: String,
    time: String,
    amount: Int,
    income: Boolean,
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = if (income) {
            CardDefaults.cardColors(
                containerColor = IncomeColor,
            )
        } else {
            CardDefaults.cardColors(
                containerColor = OutcomeColor
            )
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            CustomText(
                text = if (income) {
                    "+$amount BYN"
                } else {
                    "-$amount BYN"
                },
                fontSize = 30,
                color = Color.Black,
                modifier = Modifier.padding(start = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 22.dp, top = 18.dp, bottom = 4.dp, end = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                CustomText(
                    text = contact,
                    modifier = Modifier.padding(),
                    fontSize = 22,
                    fontWeight = FontWeight.Light
                )
                Spacer(
                    Modifier.height(5.dp)
                )
                CustomText(
                    text = time,
                    fontWeight = FontWeight.Light,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OperationCardPreview() {
    OperationCard(
        contact = "Roman Chechyotkin",
        time = "21.12.2024 17:46",
        amount = 123,
        income = false
    )
}

@Preview(showBackground = true)
@Composable
fun OperationScreenPreview() {
    //OperationsScreen()
}
