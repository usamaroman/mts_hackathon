package by.eapp.mts.presentation.ui.Contacts

import by.eapp.mts.domain.model.Contact

data class ContactsScreenState(
    val err: String = "",
    val contacts: List<Contact> = emptyList(),
    val isLoading: Boolean = false
)
