package es.aiglesiasp.petshelter.data.repositories

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import es.aiglesiasp.petshelter.domain.repositories.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): SharedPreferencesRepository {
    private val prefs = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_AUTOLOGIN = "autologin"
    }

    override suspend fun saveAutologin(autologin: Boolean) {
        prefs.edit {
            putBoolean(KEY_AUTOLOGIN, autologin)
        }
    }

    override suspend fun getAutologin(): Boolean {
        return prefs.getBoolean(KEY_AUTOLOGIN, false)
    }

    override suspend fun clearAutologin() {
        prefs.edit {
            remove(KEY_AUTOLOGIN)
        }
    }

    override suspend fun clearAll() {
        prefs.edit {
            clear()
        }
    }
}