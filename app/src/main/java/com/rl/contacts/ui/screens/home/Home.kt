package com.rl.contacts.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rl.contacts.ui.components.ContactItem


@Composable
fun Home(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    val contacts by homeViewModel.contacts.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(contacts) { contact ->
            ContactItem(
                contact = contact
            )
        }
    }

}