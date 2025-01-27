package cl.app.myjobmap.model

data class ApiResponse(
    val results: List<PhraseResult>
)

data class PhraseResult(
    val phrase: String,
    val author: String
)