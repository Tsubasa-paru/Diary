package com.example.anonymousdiaryapp.domain.usecase

import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEntriesUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    operator fun invoke(): Flow<List<DiaryEntry>> {
        return repository.getAllEntries()
    }
}