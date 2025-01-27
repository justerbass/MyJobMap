package cl.app.myjobmap.data


import cl.app.myjobmap.model.PhraseResult
import cl.app.myjobmap.util.Constants.Companion.ENDPOINT_PHRASES
import retrofit2.http.GET

interface ApiPrhases {
    @GET(ENDPOINT_PHRASES)
    suspend fun getPhrases(): List<PhraseResult>
}