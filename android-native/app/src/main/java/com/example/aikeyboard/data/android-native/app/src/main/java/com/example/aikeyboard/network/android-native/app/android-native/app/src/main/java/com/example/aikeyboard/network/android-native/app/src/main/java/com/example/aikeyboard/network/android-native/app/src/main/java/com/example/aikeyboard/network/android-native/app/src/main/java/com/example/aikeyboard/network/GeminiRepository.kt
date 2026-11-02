package com.example.aikeyboard.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GeminiRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://generativelanguage.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(GeminiApiService::class.java)

    suspend fun getAiResponse(apiKey: String, prompt: String, model: String = "gemini-1.5-flash"): String {
        val request = GeminiRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt))))
        )
        val response = apiService.generateContent(model, apiKey, request)
        if (response.isSuccessful) {
            val body = response.body()
            return body?.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No response generated."
        } else {
            return "Error: ${response.errorBody()?.string() ?: response.message()}"
        }
    }
}
