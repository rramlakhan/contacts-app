package com.rl.contacts.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rl.contacts.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getContacts(): Flow<List<Contact>>

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}