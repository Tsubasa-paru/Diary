package com.example.anonymousdiaryapp.domain.repository

import com.example.anonymousdiaryapp.data.model.DiaryEntry
import kotlinx.coroutines.flow.Flow
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository

interface DiaryRepository {
    fun getAllEntries(): Flow<List<DiaryEntry>>
    suspend fun getEntryById(id: String): DiaryEntry?
    suspend fun insertEntry(entry: DiaryEntry)
    suspend fun deleteEntry(entry: DiaryEntry)
}