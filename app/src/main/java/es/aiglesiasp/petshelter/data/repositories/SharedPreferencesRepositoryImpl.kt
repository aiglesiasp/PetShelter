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
        private const val KEY_PROFILE_ID = "profile_id"
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

    override suspend fun saveProfileId(profileId: Int) {
        prefs.edit {
            putInt(KEY_PROFILE_ID, profileId)
        }
    }

    override suspend fun getProfileId(): Int {
        return prefs.getInt(KEY_PROFILE_ID, -1)
    }

    override suspend fun clearProfileId() {
        prefs.edit {
            remove(KEY_PROFILE_ID)
        }
    }

    override suspend fun clearAll() {
        prefs.edit {
            clear()
        }
    }
}