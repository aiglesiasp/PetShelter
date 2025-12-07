package es.aiglesiasp.petshelter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import es.aiglesiasp.petshelter.framework.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        // Esto fuerza a Room a crear/abrir la BD
        // y disparará onCreate() del callback la PRIMERA vez
        CoroutineScope(Dispatchers.IO).launch {
            // Solo con acceder a writableDatabase ya se crea si no existe
            database.openHelper.writableDatabase
        }
    }
}