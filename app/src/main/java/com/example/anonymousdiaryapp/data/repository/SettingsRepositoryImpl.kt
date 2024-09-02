package com.example.anonymousdiaryapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.example.anonymousdiaryapp.data.model.Settings
import com.example.anonymousdiaryapp.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {

    private object PreferencesKeys {
        val GEMINI_API_KEY = stringPreferencesKey("gemini_api_key")
        val TEMPERATURE = floatPreferencesKey("temperature")
        val SHOW_SOLUTIONS = booleanPreferencesKey("show_solutions")
    }

    override fun getSettings(): Flow<Settings> = dataStore.data.map { preferences ->
        Settings(
            geminiApiKey = preferences[PreferencesKeys.GEMINI_API_KEY] ?: "",
            temperature = preferences[PreferencesKeys.TEMPERATURE] ?: 0.5f,
            showSolutions = preferences[PreferencesKeys.SHOW_SOLUTIONS] ?: true
        )
    }

    override suspend fun updateSettings(settings: Settings) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.GEMINI_API_KEY] = settings.geminiApiKey
            preferences[PreferencesKeys.TEMPERATURE] = settings.temperature
            preferences[PreferencesKeys.SHOW_SOLUTIONS] = settings.showSolutions
        }
    }
}