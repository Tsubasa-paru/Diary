package com.example.anonymousdiaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.util.DateConverter

@Database(entities = [DiaryEntry::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diaryEntryDao(): DiaryEntryDao
}