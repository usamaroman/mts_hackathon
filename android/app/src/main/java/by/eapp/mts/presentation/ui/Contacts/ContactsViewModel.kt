package by.eapp.mts.presentation.ui.Contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.mts.domain.use_cases.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ContactsScreenState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getContactsUseCase.invoke().collect { contacts ->
                    _state.value = _state.value.copy(contacts = contacts, isLoading = false)
                    Timber.tag("ViewModel").e("Contacts: $contacts")
                }
            } catch (e: Exception) {
                Timber.tag("ContactViewModel").e(e)
                _state.value = _state.value.copy(isLoading = false, err = e.toString())
            }
        }
    }

}