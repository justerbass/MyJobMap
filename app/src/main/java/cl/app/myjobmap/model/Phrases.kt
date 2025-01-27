package cl.app.myjobmap.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phrases")
data class Phrases(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val phrase: String,
    val author: String
)
