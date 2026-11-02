package com.example.aikeyboard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AiHistoryEntity::class], version = 1, exportSchema = false)
abstract class AiDatabase : RoomDatabase() {

    abstract fun aiHistoryDao(): AiHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AiDatabase? = null

        fun getDatabase(context: Context): AiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AiDatabase::class.java,
                    "ai_keyboard_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
