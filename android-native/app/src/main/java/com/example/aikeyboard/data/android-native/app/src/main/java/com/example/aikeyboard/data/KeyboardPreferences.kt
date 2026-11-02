package com.example.aikeyboard.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "keyboard_settings")
private val THEME_KEY = stringPreferencesKey("keyboard_theme")

val themes = listOf("Light", "Dark", "Blue", "Green")

fun keyboardTheme(context: Context): Flow<String> =
    context.dataStore.data.map { preferences ->
        preferences[THEME_KEY]?.takeIf { it in themes } ?: "Light"
    }

suspend fun saveKeyboardTheme(context: Context, theme: String) {
    val safeTheme = theme.takeIf { it in themes } ?: "Light"
    context.dataStore.edit { preferences ->
        preferences[THEME_KEY] = safeTheme
    }
}
