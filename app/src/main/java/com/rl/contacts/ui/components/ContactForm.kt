package com.rl.contacts.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.contacts.R
import com.rl.contacts.data.model.Contact

@Composable
fun ContactForm(
    contactToEdit: Contact? = null,
    onSave: (Contact) -> Unit,

) {
    var name by remember { mutableStateOf(contactToEdit?.name ?: "") }
    var phone by remember { mutableStateOf(contactToEdit?.phone ?: "") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = if (contactToEdit == null) stringResource(id = R.string.create_contact) else stringResource(id = R.string.edit_contact),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = stringResource(id = R.string.name))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            label = {
                Text(text = stringResource(id = R.string.phone))
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && phone .isNotEmpty()) {
                    val contact = Contact (
                        id = contactToEdit?.id ?: 0,
                        name = name,
                        phone = phone
                    )
                    onSave(contact)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }

}