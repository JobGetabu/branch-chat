package ke.co.branch.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DataStore {
    suspend fun saveToken(token:String )
    fun getToken( ) : Flow<String?>
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "branchchat")

class DataStoreImpl @Inject constructor(
    val context: Context
) : ke.co.branch.core.utils.DataStore {

    private val tokenKey = stringPreferencesKey("token")

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    override fun getToken(): Flow<String?>  = context.dataStore.data.map { prefs ->
        prefs[tokenKey]
    }
}