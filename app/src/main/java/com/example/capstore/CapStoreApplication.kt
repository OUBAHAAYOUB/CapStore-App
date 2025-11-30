package com.example.capstore

import android.app.Application
import com.example.capstore.data.local.AppDatabase
import com.example.capstore.data.repository.CartRepository

class CapStoreApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { CartRepository(database.cartDao()) }
}