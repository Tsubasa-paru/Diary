package com.example.anonymousdiaryapp.domain.repository

import com.example.anonymousdiaryapp.data.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<Settings>
    suspend fun updateSettings(settings: Settings)
}