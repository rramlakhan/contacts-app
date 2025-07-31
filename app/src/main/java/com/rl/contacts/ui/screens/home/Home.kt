package com.rl.contacts.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.rl.contacts.data.model.Contact
import com.rl.contacts.ui.components.ContactItem
import com.rl.contacts.utils.makePhoneCall


@Composable
fun Home(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    onItemClick: (Contact) -> Unit,
    onItemLongClick: (Contact) -> Unit
) {
    val contacts by homeViewModel.contacts.collectAsState(initial = emptyList())

    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf<String?>(null) }
    val callPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        phoneNumber?.let { phone ->
            if (isGranted) {
                makePhoneCall(context, phone)
            }
            phoneNumber = null
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(contacts) { contact ->
            ContactItem(
                contact = contact,
                onClick = {
                    onItemClick(contact)
                },
                onLongClick = {
                    onItemLongClick(contact)
                },
                onCallClick = { phone ->
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        makePhoneCall(context, phone)
                    } else {
                        phoneNumber = phone
                        callPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            )
        }
    }

}