package cl.app.myjobmap.di

import android.content.Context
import androidx.room.Room
import cl.app.myjobmap.room.PostulationDao
import cl.app.myjobmap.room.PostulationDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    @Provides
    @Singleton
    fun providesPostulationDao( postulationDataBase: PostulationDataBase): PostulationDao {
        return postulationDataBase.postulationDao()
    }

    @Singleton
    @Provides
    fun providesContactsDataBase(@ApplicationContext context: Context): PostulationDataBase {
        return Room.databaseBuilder(
            context,
            PostulationDataBase::class.java,
            "postulation_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}
