package by.eapp.mts.presentation.ui.Home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.mts.domain.use_cases.SendSpeechUseCase
import by.eapp.mts.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class SpeechToTextViewModel @Inject constructor(
    private val sendSpeechUseCase: SendSpeechUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val networkState = networkMonitor.networkStatus

    private val _speechToTextState = MutableStateFlow(SpeechToTextState())
    val speechToTextState: StateFlow<SpeechToTextState> = _speechToTextState

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    fun initializePermissionLauncher(launcher: ActivityResultLauncher<String>) {
        permissionLauncher = launcher
    }

    fun startSpeechRecognition(context: Context) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            _speechToTextState.value = SpeechToTextState(err = "Speech recognition is not available")
            return
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestRecordAudioPermission()
            return
        }

        startSpeechRecognitionInternal(context)
    }

    private fun requestRecordAudioPermission() {
        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }
    //расписать в onError код каждой ошибки для отображения на ui
    private fun startSpeechRecognitionInternal(context: Context) {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM, Locale("ru_RU"))
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, Locale("ru_RU"))
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale("ru_RU"))
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 20)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start talking")
        intent.putExtra(RecognizerIntent.RESULT_AUDIO_ERROR.toString(), "true")

        _speechToTextState.value = _speechToTextState.value.copy(isSpeaking = true, speech = "...")

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {
                _speechToTextState.value = _speechToTextState.value.copy(
                    err = "Speech recognition error: $error",
                    isSpeaking = false
                )
            }

            override fun onResults(results: Bundle?) {
                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!data.isNullOrEmpty()) {
                    _speechToTextState.value = _speechToTextState.value.copy(
                        speech = data[0],
                        isSpeaking = false,
                        textForHelp = "Recorded"
                    )
                    viewModelScope.launch {
                        sendSpeechUseCase.invoke(data[0])
                    }
                }
            }


            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        }
        )
        speechRecognizer.startListening(intent)
    }
}
