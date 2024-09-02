package com.example.anonymousdiaryapp.domain.usecase

import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import javax.inject.Inject

class DeleteEntryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(entry: DiaryEntry) {
        repository.deleteEntry(entry)
    }
}