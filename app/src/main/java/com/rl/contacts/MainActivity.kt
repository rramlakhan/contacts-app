package com.rl.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.rl.contacts.data.local.ContactsDatabase
import com.rl.contacts.data.repository.ContactsRepository
import com.rl.contacts.ui.App
import com.rl.contacts.ui.screens.home.HomeViewModel
import com.rl.contacts.ui.screens.home.HomeViewModelFactory
import com.rl.contacts.ui.theme.ContactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val contactsDao = ContactsDatabase.getDatabase(context = applicationContext).contactsDao()
        val contactsRepository = ContactsRepository(contactsDao)
        val homeViewModelFactory = HomeViewModelFactory(contactsRepository)
        val homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        setContent {
            ContactsTheme {
                App(homeViewModel)
            }
        }
    }
}
