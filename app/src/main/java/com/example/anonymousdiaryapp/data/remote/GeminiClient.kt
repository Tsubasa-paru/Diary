package com.example.anonymousdiaryapp.data.remote

import com.example.anonymousdiaryapp.data.model.Settings
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiClient @Inject constructor() {
    private var model: GenerativeModel? = null

    suspend fun generateFeedback(content: String, settings: Settings): String {
        return withContext(Dispatchers.IO) {
            try {
                initializeModel(settings)
                val prompt = buildPrompt(content, settings)
                val response = model?.generateContent(prompt)
                response?.text ?: "Unable to generate feedback"
            } catch (e: Exception) {
                "Error generating feedback: ${e.message}"
            }
        }
    }

    private fun initializeModel(settings: Settings) {
        if (model == null || model?.modelName != "gemini-pro") {
            model = GenerativeModel(
                modelName = "gemini-pro",
                apiKey = settings.geminiApiKey,
                generationConfig = generationConfig {
                    temperature = settings.temperature
                }
            )
        }
    }

    private fun buildPrompt(content: String, settings: Settings): String {
        return buildString {
            append("次の情報を基に、短文でコメントしてください。")
            append("提供される情報はユーザの悪感情の吐露です。")
            append("内容には、以下の点に注意してください。")
            append("情報が少なくても、補完して**必ず**コメントをしてください。")
            append("設定の内容に準拠してください。")
            append("**解決方法やアドバイスを求めるまでは、具体的な解決策を提案しないでください。**")
            if (settings.showSolutions) {
                append("解決方法やアドバイスを教えてください。")
            }
            append("\n\nDiary entry: $content")
        }
    }
}