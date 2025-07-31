package com.rl.contacts.data.repository

import com.rl.contacts.data.local.dao.ContactsDao
import com.rl.contacts.data.model.Contact
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val contactsDao: ContactsDao) {

    val contacts: Flow<List<Contact>> = contactsDao.getContacts()

    suspend fun addContact(contact: Contact) {
        contactsDao.insert(contact)
    }

    suspend fun updateContact(contact: Contact) {
        contactsDao.update(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactsDao.delete(contact)
    }
}