package cl.app.myjobmap.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.app.myjobmap.model.Phrases
import cl.app.myjobmap.repository.PhrasesRepository
import cl.app.myjobmap.state.PhrasesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


//    API

@HiltViewModel
class PhrasesViewModel @Inject constructor(private val phrasesRepository: PhrasesRepository) : ViewModel() {

    var state by mutableStateOf(PhrasesState())
        private set

    val phrases : Flow<List<Phrases>> by lazy{
        phrasesRepository.getAllPhrasesRoom()

    }

    fun getAllApi(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                phrasesRepository.getPhrasesApi()

            }
        }
    }

    fun clean(){
        state = state.copy(
            phrases = "",
            author = ""
        )
    }
}