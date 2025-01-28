package cl.app.myjobmap.di

import cl.app.myjobmap.data.ApiPrhases
import cl.app.myjobmap.repository.PhrasesRepository
import cl.app.myjobmap.repository.PhrasesRepositoryImpl
import cl.app.myjobmap.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providesBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    }

    @Singleton
    @Provides
    fun providesPrhasesApi(retrofit: Retrofit): ApiPrhases {
        return retrofit.create(ApiPrhases::class.java)

    }

    @Singleton
    @Provides
    fun providesPhrasesRepository(api: ApiPrhases): PhrasesRepository {
        return PhrasesRepositoryImpl(api)
    }


}