package cl.app.myjobmap.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.app.myjobmap.repository.PhrasesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


//    API

@HiltViewModel
class PhrasesViewModel @Inject constructor(private val phrasesRepository: PhrasesRepository) :
    ViewModel() {

    fun getAllApi() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                phrasesRepository.getPhrasesApi()
            }
        }
    }

}