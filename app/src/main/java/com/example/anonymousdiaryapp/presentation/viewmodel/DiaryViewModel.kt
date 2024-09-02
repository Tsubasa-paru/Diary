package com.example.anonymousdiaryapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anonymousdiaryapp.data.model.DiaryEntry
import com.example.anonymousdiaryapp.domain.usecase.AddEntryUseCase
import com.example.anonymousdiaryapp.domain.usecase.DeleteEntryUseCase
import com.example.anonymousdiaryapp.domain.usecase.GetAllEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.anonymousdiaryapp.domain.repository.DiaryRepository
import com.example.anonymousdiaryapp.domain.repository.SettingsRepository
import com.example.anonymousdiaryapp.data.remote.GeminiClient
import kotlinx.coroutines.flow.first
import java.util.Date
import java.util.UUID

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository,
    private val settingsRepository: SettingsRepository,
    private val geminiClient: GeminiClient,
    private val getAllEntriesUseCase: GetAllEntriesUseCase,
    private val addEntryUseCase: AddEntryUseCase,
    private val deleteEntryUseCase: DeleteEntryUseCase
) : ViewModel() {

    private val _entries = MutableStateFlow<List<DiaryEntry>>(emptyList())
    val entries: StateFlow<List<DiaryEntry>> = _entries

    init {
        loadEntries()
    }

    private fun loadEntries() {
        viewModelScope.launch {
            diaryRepository.getAllEntries()
                .catch { /* エラー処理 */ }
                .collect { _entries.value = it }
        }
    }

    fun addEntry(content: String, mood: String) {
        viewModelScope.launch {
            val newEntry = DiaryEntry(
                id = UUID.randomUUID().toString(),
                content = content,
                mood = mood,
                createdAt = Date(),
                updatedAt = Date()
            )
            diaryRepository.insertEntry(newEntry)
        }
    }

    fun deleteEntry(entry: DiaryEntry) {
        viewModelScope.launch {
            diaryRepository.deleteEntry(entry)
        }
    }

    private val _feedback = MutableStateFlow<String?>(null)
    val feedback: StateFlow<String?> = _feedback

    fun generateFeedback(entryId: String) {
        viewModelScope.launch {
            val entry = diaryRepository.getEntryById(entryId)
            val settings = settingsRepository.getSettings().first()
            entry?.let {
                val feedbackContent = geminiClient.generateFeedback(it.content, settings)
                _feedback.value = feedbackContent
            }
        }
    }
}