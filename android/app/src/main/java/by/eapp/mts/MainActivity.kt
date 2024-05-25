package by.eapp.mts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import by.eapp.mts.presentation.navigation.NavGraph
import by.eapp.mts.presentation.ui.Contacts.ContactsViewModel
import by.eapp.mts.presentation.ui.Home.SpeechToTextViewModel
import by.eapp.mts.ui.theme.MtsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            speechToTextViewModel.startSpeechRecognition(applicationContext)
        } else {
            Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val speechToTextViewModel: SpeechToTextViewModel by viewModels()
    private val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MtsTheme {
                //speechToTextViewModel.initializeTextToSpeech(requireContext())
                    val navController = rememberNavController()

                    speechToTextViewModel.initialize(permissionLauncher, navController)
                    NavGraph(navController = navController, speechToTextViewModel = speechToTextViewModel, contactsViewModel = contactsViewModel)
                

            }
        }
    }
}


