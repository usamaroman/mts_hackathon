package by.eapp.mts.presentation.ui.Contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.eapp.mts.domain.model.Contact
import by.eapp.mts.presentation.navigation.BottomBar
import by.eapp.mts.presentation.utils.CustomText
import coil.compose.AsyncImage
import java.time.format.TextStyle

@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) },
        containerColor = Color.White
    ) {
        it
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(state.contacts) { contact ->
                ContactItem(contact = contact)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    contact: Contact,
) {

        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
                ,
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 4.dp
            )
        ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = contact.contactIcon,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                CustomText(
                    text = contact.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .semantics {
                            contentDescription = contact.name
                        },
                    fontSize = 18,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    )
                CustomText(
                    text = contact.phoneNumber,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    fontSize = 14,
                    color = Color.LightGray,
                    textAlign = TextAlign.Start
                )
            }
        }
        }

}

@Preview(showBackground = true)
@Composable
fun previewContactItem() {
    ContactItem(contact = Contact(
        id = "",
        name = "Roman Chechyotkin",
        contactIcon = "",
        phoneNumber = "+375 44 753 4067",
        balance = 30
    ))
}