package cl.app.myjobmap.repository

import cl.app.myjobmap.model.Postulation
import cl.app.myjobmap.room.PostulationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostulationRepository @Inject constructor(private val postulationDao: PostulationDao) {

    suspend fun insertPostulation(postulation: Postulation) = postulationDao.insertPostulation(postulation)
    suspend fun updatePostulation(postulation: Postulation) = postulationDao.updatePostulation(postulation)
    suspend fun deletePostulation(postulation: Postulation) = postulationDao.deletePostulation(postulation)
    fun getAllPostulations() = postulationDao.getAllPostulations().flowOn(Dispatchers.IO).conflate()

}