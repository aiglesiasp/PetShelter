package es.aiglesiasp.petshelter.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.aiglesiasp.petshelter.framework.database.AppDatabase
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.framework.database.PetLocal
import es.aiglesiasp.petshelter.framework.database.ShelterLocal
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        INSTANCE?.let { return it }

        val instance = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addCallback(object: RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    // Cuando se crea por primera vez, precargamos datos
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.let { database ->
                            prepopulateDatabase(appContext, database)
                        }
                    }
                }
            }).build()

        INSTANCE = instance
        return instance
    }

    @Provides
    fun providePetDao(database: AppDatabase) = database.petDao()

    @Provides
    fun provideShelterDao(database: AppDatabase) = database.shelterDao()

    // --------- FUNCIÓN DE PRECARGA ---------
    private suspend fun prepopulateDatabase(context: Context, db: AppDatabase) {
        val json = Json { ignoreUnknownKeys = true }

        // Leer shelters.json desde res/raw
        val sheltersJson = context.resources
            .openRawResource(R.raw.shelters)
            .bufferedReader()
            .use { it.readText() }

        val shelters = json.decodeFromString<List<ShelterLocal>>(sheltersJson)
        db.shelterDao().insertShelters(shelters)

        // Leer pets.json desde res/raw
        val petsJson = context.resources
            .openRawResource(R.raw.pets)
            .bufferedReader()
            .use { it.readText() }

        val pets = json.decodeFromString<List<PetLocal>>(petsJson)
        db.petDao().insertPets(pets)
    }
}