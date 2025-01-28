package cl.app.myjobmap.repository


import cl.app.myjobmap.data.ApiPrhases
import cl.app.myjobmap.model.PhraseDay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PhrasesRepository {
    suspend fun getPhrasesApi(): PhraseDay
}

class PhrasesRepositoryImpl @Inject constructor(
    private val api: ApiPrhases,
) : PhrasesRepository {
    override suspend fun getPhrasesApi(): PhraseDay {

        return api.getPhrases()
    }
}