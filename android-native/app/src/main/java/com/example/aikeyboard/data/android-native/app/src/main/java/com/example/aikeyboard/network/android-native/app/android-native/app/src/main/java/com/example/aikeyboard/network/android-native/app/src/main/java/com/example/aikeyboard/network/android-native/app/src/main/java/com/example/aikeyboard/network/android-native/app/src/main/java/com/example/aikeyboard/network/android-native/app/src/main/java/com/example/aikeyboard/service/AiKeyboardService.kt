package com.example.aikeyboard.service

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.aikeyboard.R
import com.example.aikeyboard.data.AiDatabase
import com.example.aikeyboard.data.AiHistoryEntity
import com.example.aikeyboard.network.GeminiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AiKeyboardService : InputMethodService() {

    private val repository = GeminiRepository()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateInputView(): View {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val textView = TextView(this).apply {
            text = "AI Keyboard Ready"
            setPadding(20, 20, 20, 20)
        }

        val button = Button(this).apply {
            text = "Generate AI Text"
            setOnClickListener {
                coroutineScope.launch {
                    val response = withContext(Dispatchers.IO) {
                        try {
                            // এখানে আপনার জেমিনি এপিআই কি দিন অথবা ডেটাস্টোর থেকে ফেচ করুন
                            repository.getAiResponse("YOUR_API_KEY", "Hello, write a friendly greeting.")
                        } catch (e: Exception) {
                            "Error: ${e.localizedMessage}"
                        }
                    }
                    textView.text = response
                }
            }
        }

        layout.addView(textView)
        layout.addView(button)
        return layout
    }
}
