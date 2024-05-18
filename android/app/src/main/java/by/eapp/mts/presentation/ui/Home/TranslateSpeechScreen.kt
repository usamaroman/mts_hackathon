package by.eapp.mts.presentation.ui.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.eapp.mts.presentation.navigation.BottomBar
import by.eapp.mts.presentation.ui.Home.utils.bounceClick

@Composable
fun TranslateSpeechScreen(
    navController: NavHostController,
    viewModel: SpeechToTextViewModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val speechToTextState by viewModel.speechToTextState.collectAsState()

    val networkStatus by viewModel.networkState.collectAsState()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {

        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!networkStatus) {
                    Text(
                        text = "No internet connection",
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = "Speak something:",
                        fontSize = 25.sp,
                        color = Color.Black
                    )
                    MicrophoneButton(onClick = {
                        viewModel.startSpeechRecognition(context)
                    }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = when {
                            speechToTextState.isSpeaking -> "Recording..."
                            speechToTextState.speech.isNotEmpty() -> "Recorded: ${speechToTextState.speech}"
                            else -> ""
                        },
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    if (speechToTextState.err != null) {
                        Text(
                            text = "Error: ${speechToTextState.err}",
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MicrophoneButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .semantics {
                contentDescription = "Start speech"
            }
            .padding(10.dp)
            .size(60.dp)
            .bounceClick(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Microphone",
            modifier = Modifier
                .align(Alignment.CenterVertically),
            tint = Color.Black,

        )
    }
}