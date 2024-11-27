package cl.app.myjobmap.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.app.myjobmap.model.Postulation
import kotlinx.coroutines.flow.Flow

@Dao
interface PostulationDao {

    @Query("SELECT * FROM postulations")
    fun getAllPostulations(): Flow<List<Postulation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostulation(postulation: Postulation)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePostulation(postulation: Postulation)

    @Delete
    suspend fun deletePostulation(postulation: Postulation)

}