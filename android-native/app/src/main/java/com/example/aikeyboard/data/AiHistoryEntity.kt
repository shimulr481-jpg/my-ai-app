package com.example.aikeyboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ai_history")
data class AiHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val prompt: String,
    val response: String,
    val timestamp: Long = System.currentTimeMillis()
)
