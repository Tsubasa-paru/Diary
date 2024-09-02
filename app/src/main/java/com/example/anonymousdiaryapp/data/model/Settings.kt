package com.example.anonymousdiaryapp.data.model

data class Settings(
    val geminiApiKey: String = "",
    val temperature: Float = 0.5f,
    val showSolutions: Boolean = true
)