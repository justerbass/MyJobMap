package cl.app.myjobmap.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.app.myjobmap.model.Phrases
import kotlinx.coroutines.flow.Flow

@Dao
interface PhrasesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhrases(phrases: Phrases)

    @Query("SELECT * FROM Phrases ORDER BY id")
    fun getAllPhrases(): Flow<List<Phrases>>

}