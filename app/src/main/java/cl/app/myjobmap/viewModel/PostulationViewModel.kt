package cl.app.myjobmap.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.app.myjobmap.model.Postulation
import cl.app.myjobmap.repository.PostulationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostulationViewModel @Inject constructor(private val postulationRepository: PostulationRepository) : ViewModel() {

//    DATABASE
    fun getAllPostulations() : Flow<List<Postulation>> {
      return postulationRepository.getAllPostulations()
    }

    fun insertPostulation(postulation: Postulation) = viewModelScope.launch (Dispatchers.IO){
        postulationRepository.insertPostulation(postulation)
    }

    fun updatePostulation(postulation: Postulation) = viewModelScope.launch (Dispatchers.IO){
        postulationRepository.updatePostulation(postulation)
    }

    fun deletePostulation(postulation: Postulation) = viewModelScope.launch (Dispatchers.IO){
        postulationRepository.deletePostulation(postulation)
    }

//    answer Job
    private var _answerJob = mutableStateOf("")
    var answerJob = _answerJob

}