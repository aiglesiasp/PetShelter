package es.aiglesiasp.petshelter.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import es.aiglesiasp.petshelter.framework.PetDao
import es.aiglesiasp.petshelter.framework.ShelterDao

@Database(entities = [PetLocal::class, ShelterLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun shelterDao(): ShelterDao
}