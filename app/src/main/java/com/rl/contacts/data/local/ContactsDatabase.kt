package com.rl.contacts.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rl.contacts.data.local.dao.ContactsDao
import com.rl.contacts.data.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase: RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getDatabase(context: Context): ContactsDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "contacts_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}