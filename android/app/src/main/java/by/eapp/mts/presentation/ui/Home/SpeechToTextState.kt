package by.eapp.mts.presentation.ui.Home



data class SpeechToTextState(
    val err: String? = null,
    val isSpeaking: Boolean = false,
    val speech: String = "",
    val onStartSpeech: Boolean = false,
    val textForHelp: String = "",
)
