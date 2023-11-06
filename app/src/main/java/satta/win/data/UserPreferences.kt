package net.simplifiedcoding.data

import android.content.Context
import android.widget.TextView
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }
    val loginUserID: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[LOGIN_USER_ID]
        }

    val Userid: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USERID]
        }

    val name: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[NAME]
        }

    val email: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[EMAIl]
        }

    suspend fun saveAuthToken(authToken: String,loginUserId: String,name: String,Userid: String,email: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
            preferences[LOGIN_USER_ID]=loginUserId
            preferences[NAME]=name
            preferences[USERID]=Userid
            preferences[EMAIl]=email
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val LOGIN_USER_ID = preferencesKey<String>("LOGIN_USER_ID")
        private val NAME = preferencesKey<String>("NAME")
        private val USERID = preferencesKey<String>("USERID")
        private val EMAIl = preferencesKey<String>("EMAIl")


        var dialogCon :String?="1";
         var textItemCount : TextView?=null
    }

}