import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import androidx.activity.result.ActivityResultLauncher
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import by.eapp.mts.domain.use_cases.SendSpeechUseCase
import by.eapp.mts.network.NetworkMonitor
import by.eapp.mts.presentation.ui.Home.SpeechToTextViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


class SpeechToTextViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var viewModel: SpeechToTextViewModel
    private val sendSpeechUseCase: SendSpeechUseCase = mock()
    private val networkMonitor: NetworkMonitor = mock()
    private val context: Context = mock(Context::class.java)
    private val packageManager: PackageManager = mock(PackageManager::class.java)
    private val permissionLauncher: ActivityResultLauncher<String> = mock()


//    private val testCoroutineSheduler = TestCoroutineScheduler()
//    private val testDispatcher: TestDispatcher(testCoroutineSheduler)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Default)

        this.viewModel = SpeechToTextViewModel(sendSpeechUseCase, networkMonitor)
        this.viewModel.initialize(permissionLauncher)

        `when`(context.packageManager).thenReturn(packageManager)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        //testDispatcher.cleanupTestCoroutines()
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testStartSpeechRecognition_whenSpeechRecognizerNotAvailable() = runTest {

        mockStatic(SpeechRecognizer::class.java).use { mockedSpeechRecognizer ->

            mockedSpeechRecognizer.`when`<Boolean> { SpeechRecognizer.isRecognitionAvailable(context) }
                .thenReturn(false)

            viewModel.startSpeechRecognition(context)

            assert(viewModel.speechToTextState.value.err == "Speech recognition is not available")
            assert(!viewModel.speechToTextState.value.isSpeaking)
        }
    }

//решить проблему с NPE
    @Test
    fun testStartSpeechRecognition_whenPermissionNotGranted() = runTest {

        `when`(context.checkPermission(eq(android.Manifest.permission.RECORD_AUDIO), any(), any()))
            .thenReturn(PackageManager.PERMISSION_DENIED)


        viewModel.startSpeechRecognition(context)

        verify(permissionLauncher).launch(android.Manifest.permission.RECORD_AUDIO)
    }

    @Test
    fun testStartSpeechRecognitionInternal_onResults() = runTest {

        val resultsBundle = mock(Bundle::class.java)
        `when`(resultsBundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION))
            .thenReturn(arrayListOf("Test speech"))


        `when`(context.packageManager).thenReturn(packageManager)
        `when`(
            packageManager.queryIntentActivities(
                any(Intent::class.java),
                eq(PackageManager.MATCH_DEFAULT_ONLY)
            )
        ).thenReturn(listOf(mock()))
        `when`(context.checkPermission(eq(android.Manifest.permission.RECORD_AUDIO), any(), any()))
            .thenReturn(PackageManager.PERMISSION_GRANTED)
        val speechRecognizer = mock(SpeechRecognizer::class.java)
        `when`(SpeechRecognizer.createSpeechRecognizer(context)).thenReturn(speechRecognizer)

        this@SpeechToTextViewModelTest.viewModel.startSpeechRecognition(context)


        val recognitionListener = mock(RecognitionListener::class.java)
        doAnswer { _ ->

            recognitionListener.onResults(resultsBundle)
            null
        }.`when`(speechRecognizer).setRecognitionListener(any())

        recognitionListener.onResults(resultsBundle)


        assert(this@SpeechToTextViewModelTest.viewModel.speechToTextState.value.speech == "Test speech")
        assert(!this@SpeechToTextViewModelTest.viewModel.speechToTextState.value.isSpeaking)
        assert(this@SpeechToTextViewModelTest.viewModel.speechToTextState.value.textForHelp == "Recorded")
        verify(sendSpeechUseCase).invoke("Test speech")
    }

//сделать тест для ласт функции

}
