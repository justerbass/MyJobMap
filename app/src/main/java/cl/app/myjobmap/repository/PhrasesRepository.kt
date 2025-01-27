package cl.app.myjobmap.repository


import cl.app.myjobmap.data.ApiPrhases
import cl.app.myjobmap.model.PhraseResult
import cl.app.myjobmap.model.Phrases
import cl.app.myjobmap.room.PhrasesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PhrasesRepository {
    suspend fun getPhrasesApi(): List<PhraseResult>
    fun getAllPhrasesRoom(): Flow<List<Phrases>>
}

class PhrasesRepositoryImpl @Inject constructor(
    private val api: ApiPrhases,
    private val dao: PhrasesDao
) : PhrasesRepository {
    override suspend fun getPhrasesApi(): List<PhraseResult> {
        val dataPhrases = api.getPhrases()
        dataPhrases.forEach {
            val phrase = Phrases(
                phrase = it.phrase,
                author = it.author
            )
            dao.insertPhrases(phrase)

        }
        return ArrayList(dataPhrases)
    }

    override fun getAllPhrasesRoom(): Flow<List<Phrases>> = dao.getAllPhrases()
}