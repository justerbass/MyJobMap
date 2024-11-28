package cl.app.myjobmap.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postulations")
data class Postulation (
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,

    @ColumnInfo(name = "job")
    val job: String,

    @ColumnInfo(name = "company")
    val company: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "jobDescription")
    val jobDescription: String,

    @ColumnInfo(name = "salary")
    val salary: String,

    @ColumnInfo(name = "recruiter")
    val recruiter: String

)