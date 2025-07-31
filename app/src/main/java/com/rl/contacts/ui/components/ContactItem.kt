package com.rl.contacts.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.contacts.R
import com.rl.contacts.data.model.Contact

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactItem(contact: Contact, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
            .combinedClickable(
                onClick = onClick
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(.9f)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = contact.phone,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = stringResource(id = R.string.call)
            )
        }
    }
}