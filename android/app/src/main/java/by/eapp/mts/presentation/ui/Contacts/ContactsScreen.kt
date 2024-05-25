package by.eapp.mts.presentation.ui.Contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import by.eapp.mts.domain.model.Contact
import by.eapp.mts.presentation.navigation.BottomBar
import by.eapp.mts.presentation.utils.CustomText
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    viewModel: ContactsViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val state by viewModel.state.collectAsState()

    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) },
        containerColor = Color.White
    ) {
        it
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(top = 8.dp)
        ) {
//            TextField(
//                value = searchText,
//                onValueChange = viewModel::onSearchTextChanged,
//                modifier = Modifier.fillMaxWidth(0.9f),
//                placeholder = { Text(text = "Search..") }
//            )
            SearchBar(
                searchText = searchText,
                onSearchTextChanged = viewModel::onSearchTextChanged
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = 16.dp)
                ) {

                    items(searchResults) { contact ->
                        ContactItem(contact = contact)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    searchText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier.fillMaxWidth(0.95f),
        placeholder = { Text(text = "Search..") },
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = Color.DarkGray,
            focusedPlaceholderColor = Color.LightGray,
            unfocusedPlaceholderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(10.dp)
    )
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
            .padding(start = 4.dp, end = 4.dp),
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
    ContactItem(
        contact = Contact(
            id = "",
            name = "Roman Chechyotkin",
            contactIcon = "",
            phoneNumber = "+375 44 753 4067",

            )
    )
}