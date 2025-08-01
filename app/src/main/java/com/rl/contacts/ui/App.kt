package com.rl.contacts.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rl.contacts.R
import com.rl.contacts.data.model.Contact
import com.rl.contacts.ui.components.ContactForm
import com.rl.contacts.ui.components.DeleteConfirmDialog
import com.rl.contacts.ui.screens.home.Home
import com.rl.contacts.ui.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(homeViewModel: HomeViewModel) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var contactToDelete by remember { mutableStateOf<Contact?>(null) }

    if (contactToDelete != null) {
        DeleteConfirmDialog(
            onDeleteConfirm = {
                homeViewModel.deleteContact(contactToDelete!!)
                contactToDelete = null
            },
            onDismiss = {
                contactToDelete = null
            }
        )
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                isBottomSheetVisible = false
                selectedContact = null
            },
            sheetState = sheetState
        ) {
            ContactForm(
                contactToEdit = selectedContact,
                onSave = { contact ->
                    if (contact.id == 0) {
                        homeViewModel.addContact(contact)
                    } else {
                        homeViewModel.updateContact(contact)
                    }
                    isBottomSheetVisible = false
                    selectedContact = null
                }
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isBottomSheetVisible = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) { innerPadding ->

        Home(
            modifier = Modifier.padding(innerPadding),
            homeViewModel = homeViewModel,
            onItemClick = { contact ->
                selectedContact = contact
                isBottomSheetVisible = true
            },
            onItemLongClick = { contact ->
                contactToDelete = contact
            }
        )
    }
}