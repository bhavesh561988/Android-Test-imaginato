package com.iml.myapplication.injection.modules

import android.content.Context
import com.iml.myapplication.room.DatabaseManager
import dagger.Module
import dagger.Provides

@Module
class DatabaseManagerModule(private val context: Context) {

    val databaseManager: DatabaseManager
        @Provides
        get() = DatabaseManager(context = context)
}