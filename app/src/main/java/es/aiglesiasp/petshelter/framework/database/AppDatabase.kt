package es.aiglesiasp.petshelter.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PetLocal::class, ShelterLocal::class, LoginLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun petDao(): PetDao
    abstract fun shelterDao(): ShelterDao
    abstract fun loginDao(): LoginDao
}