package cl.app.myjobmap.room


import androidx.room.Database
import androidx.room.RoomDatabase
import cl.app.myjobmap.model.Phrases

@Database(
    entities = [Phrases::class],
    version = 1
)

abstract class PhraseDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhrasesDao
}