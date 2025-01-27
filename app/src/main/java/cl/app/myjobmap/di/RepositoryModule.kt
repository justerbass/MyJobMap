package cl.app.myjobmap.di

import cl.app.myjobmap.repository.PhrasesRepository
import cl.app.myjobmap.repository.PhrasesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Singleton
    @Binds
    abstract fun phrasesRepository(repo: PhrasesRepositoryImpl): PhrasesRepository
}