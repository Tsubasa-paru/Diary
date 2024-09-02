package com.example.anonymousdiaryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "diary_entries")
data class DiaryEntry(
    @PrimaryKey val id: String,
    val content: String,
    val mood: String,
    val createdAt: Date,
    val updatedAt: Date
)