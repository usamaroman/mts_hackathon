package by.eapp.mts.domain.use_cases

import by.eapp.mts.domain.repository.ApiInteractionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SendSpeechUseCase @Inject constructor(
    private val repository: ApiInteractionRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke(request: String) = withContext(dispatcher) {
        repository.sendSpeech(
            request
        )
    }
}

class GetContactsUseCase @Inject constructor(
    private val repository: ApiInteractionRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke() = withContext(dispatcher) { repository.getContacts() }
}
