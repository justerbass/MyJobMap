package cl.app.myjobmap.repository

import cl.app.myjobmap.model.Postulation
import cl.app.myjobmap.room.PostulationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostulationRepository @Inject constructor(private val postulationDao: PostulationDao) {

    fun getAllPostulations() = postulationDao.getAllPostulations().flowOn(Dispatchers.IO).conflate()
    fun getPostulationById(id: Int) = postulationDao.getPostulationById(id).flowOn(Dispatchers.IO).conflate()
    suspend fun insertPostulation(postulation: Postulation) = postulationDao.insertPostulation(postulation)
    suspend fun updatePostulation(postulation: Postulation) = postulationDao.updatePostulation(postulation)
    suspend fun deletePostulation(postulation: Postulation) = postulationDao.deletePostulation(postulation)

}