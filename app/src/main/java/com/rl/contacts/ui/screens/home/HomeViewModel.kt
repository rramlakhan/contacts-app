package com.rl.contacts.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rl.contacts.data.model.Contact
import com.rl.contacts.data.repository.ContactsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val contactsRepository: ContactsRepository): ViewModel() {

    val contacts = contactsRepository.contacts

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            contactsRepository.addContact(contact)
        }
    }
}

class HomeViewModelFactory(private val contactsRepository: ContactsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(contactsRepository) as T
    }
}