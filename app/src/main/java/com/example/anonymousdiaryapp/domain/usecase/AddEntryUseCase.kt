package com.example.anonymousdiaryapp.domain.usecase

import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import java.util.*
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(content: String, mood: String) {
        val newEntry = DiaryEntry(
            id = UUID.randomUUID().toString(),
            content = content,
            mood = mood,
            createdAt = Date(),
            updatedAt = Date()
        )
        repository.insertEntry(newEntry)
    }
}