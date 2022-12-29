package com.uc.lechef.helper

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StoreUserID(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("userId")
        val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    //get the saved email
    val getUserId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY] ?: ""
        }

    //save email into datastore
    suspend fun setUserId(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = name
        }
    }

    suspend fun deleteUserId() {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = ""
        }
    }
}