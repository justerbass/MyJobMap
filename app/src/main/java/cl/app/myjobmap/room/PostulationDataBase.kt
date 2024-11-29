package cl.app.myjobmap.room

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.app.myjobmap.model.Postulation

@Database(entities = [Postulation::class], version = 2, exportSchema = false)
abstract class PostulationDataBase : RoomDatabase() {
    abstract fun postulationDao(): PostulationDao
}