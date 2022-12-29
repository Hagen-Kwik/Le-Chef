package com.uc.lechef.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserCookie(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("cookie")
        val cookie_User = stringPreferencesKey("cookie")
    }

    //get the saved email
    val getCookie: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[cookie_User] ?: ""
        }

    //save email into datastore
    suspend fun setCookie(name: String) {
        context.dataStore.edit { preferences ->
            preferences[cookie_User] = name
        }
    }

    suspend fun deleteCookie() {
        context.dataStore.edit { preferences ->
            preferences[cookie_User] = ""
        }
    }
}