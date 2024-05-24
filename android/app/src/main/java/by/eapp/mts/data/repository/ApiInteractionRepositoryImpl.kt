package by.eapp.mts.data.repository

import androidx.room.util.query
import by.eapp.mts.GetAllUserNamesQuery
import by.eapp.mts.data.remote.ApiService
import by.eapp.mts.data.remote.model.SpeechRequest
import by.eapp.mts.data.toContact
import by.eapp.mts.domain.model.Contact
import by.eapp.mts.domain.repository.ApiInteractionRepository
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class ApiInteractionRepositoryImpl(
    private val apiService: ApiService,
    private val apolloClient: ApolloClient,
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
                val response = apolloClient.query(GetAllUserNamesQuery()).execute()
               val users = response.data?.users
                if (response.data != null) {
                    Timber.tag("APiRepository").d("Success")
                    emit(users?.map { it.toContact() } ?: emptyList())
                } else {
                    Timber.tag("APiRepository").e("Unsuccessful response: %s", response.errors)
                    emit(emptyList())
                }
            } catch (e: Exception) {
                Timber.tag("APiRepository").e(e, "Exception occurred: %s", e.message)
                emit(emptyList())
            }
        }
    }

}
