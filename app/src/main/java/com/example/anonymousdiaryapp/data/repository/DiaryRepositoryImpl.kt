package com.example.anonymousdiaryapp.data.repository

import com.example.anonymousdiaryapp.data.local.DiaryEntryDao
import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryEntryDao: DiaryEntryDao
) : DiaryRepository {
    override fun getAllEntries(): Flow<List<DiaryEntry>> {
        return diaryEntryDao.getAllEntries()
    }

    override suspend fun getEntryById(id: String): DiaryEntry? {
        return diaryEntryDao.getEntryById(id)
    }

    override suspend fun insertEntry(entry: DiaryEntry) {
        diaryEntryDao.insertEntry(entry)
    }

    override suspend fun deleteEntry(entry: DiaryEntry) {
        diaryEntryDao.deleteEntry(entry)
    }
}