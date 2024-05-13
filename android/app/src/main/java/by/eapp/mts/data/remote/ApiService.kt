package by.eapp.mts.data.remote

import by.eapp.mts.data.remote.model.ContactDto
import by.eapp.mts.data.remote.model.ContactResponse
import by.eapp.mts.data.remote.model.SpeechRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("speech")
    suspend fun sendSpeech(@Body request: SpeechRequest)

    @GET("contacts")
    suspend fun getContacts(): Response<ContactResponse>

}