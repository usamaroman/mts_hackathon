package by.eapp.mts.domain.repository

import by.eapp.mts.GetAllUserNamesQuery
import by.eapp.mts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ApiInteractionRepository {
    suspend fun sendSpeech(request: String)
    suspend fun getContacts(): Flow<List<Contact>>
}