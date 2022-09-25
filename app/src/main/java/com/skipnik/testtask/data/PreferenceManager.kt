package com.skipnik.testtask.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext context: Context) {

    private val preferencesDataStore = context.dataStore

    val preferencesFlow: Flow<String> = preferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.LINK] ?: ""
        }

    suspend fun updateLink(newLink: String) {
        preferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.LINK] = newLink
        }
    }

    private object PreferencesKeys {
        val LINK = stringPreferencesKey("link")
    }


}
