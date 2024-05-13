package by.eapp.mts.data.repository

import by.eapp.mts.data.remote.ApiService
import by.eapp.mts.data.remote.model.SpeechRequest
import by.eapp.mts.data.toContact
import by.eapp.mts.domain.model.Contact
import by.eapp.mts.domain.repository.ApiInteractionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class ApiInteractionRepositoryImpl(
    private val apiService: ApiService,
) : ApiInteractionRepository {

    override suspend fun sendSpeech(request: String) {
        return try {
            apiService.sendSpeech(SpeechRequest(request))
            Timber.tag("APiRepository").d("Success")
        } catch (e: Exception) {
            Timber.tag("APiRepository").e(e, "Exception occurred: %s", e.message)
        }
    }

    override suspend fun getContacts(): Flow<List<Contact>> {
        return flow {
            try {
                val response = apiService.getContacts()
                if (response.isSuccessful) {
                    Timber.tag("APiRepository").d("Success")
                    val contacts = response.body()?.contacts?.map {
                        it.toContact()
                    } ?: emptyList()
                    emit(contacts)
                } else {
                    Timber.tag("APiRepository").e("Unsuccessful response: %s", response.code())
                    emit(emptyList())
                }
            } catch (e: Exception) {
                Timber.tag("APiRepository").e(e, "Exception occurred: %s", e.message)
                emit(emptyList())
            }
        }
    }

}
