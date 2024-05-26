package by.eapp.mts.presentation.ui.Contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.eapp.mts.domain.model.Contact
import by.eapp.mts.domain.use_cases.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ContactsScreenState(isLoading = true))
    val state = _state.asStateFlow()

    private val _searchingContacts = MutableStateFlow<List<Contact>>(emptyList())

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Contact>>(emptyList())
    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getContactsUseCase.invoke().collect { contacts ->
                    _state.value = _state.value.copy(contacts = contacts, isLoading = false)
                    _searchingContacts.update {
                        contacts
                    }
                    Timber.tag("ViewModel").e("Contacts: $contacts")
                }
            } catch (e: Exception) {
                Timber.tag("ContactViewModel").e(e)
                _state.value = _state.value.copy(isLoading = false, err = e.toString())
            }
        }
    }


    @OptIn(FlowPreview::class)
    val searchResults = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_searchingContacts) {
        text, contacts ->
        if (text.isBlank()) {
            contacts
        } else {
            contacts.filter {
                it.name.contains(text, ignoreCase = true)
            }
        }
    }
        .onEach {
            _isSearching.update { false }
        }
            .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(3000),
        initialValue = emptyList()
    )

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
    }

    fun onToggleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChanged("")
        }
    }







}