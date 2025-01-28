package cl.app.myjobmap.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.app.myjobmap.model.PhraseDay
import cl.app.myjobmap.repository.PhrasesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


//    API

@HiltViewModel
class PhrasesViewModel @Inject constructor(private val phrasesRepository: PhrasesRepository) :
    ViewModel() {

    private val _phrase = MutableStateFlow<PhraseDay?>(null)

    val phrase: MutableStateFlow<PhraseDay?> = _phrase


    fun getAllApi() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                phrasesRepository.getPhrasesApi()
            }
            _phrase.value = result
        }
    }


}