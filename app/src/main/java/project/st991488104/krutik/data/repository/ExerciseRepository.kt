package project.st991488104.krutik.data.repository
//Krutik Parikh | 991488104

import androidx.lifecycle.LiveData
import project.st991488104.krutik.data.Dao.ExerciseDao
import project.st991488104.krutik.data.models.ExerciseData

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    val getAllData: LiveData<List<ExerciseData>> = exerciseDao.getAllData()

    suspend fun insertData(exerciseData: ExerciseData){
        exerciseDao.insertData(exerciseData)
    }

    suspend fun updateData(exerciseData: ExerciseData){
        exerciseDao.updateData(exerciseData)
    }

    suspend fun deleteItem(exerciseData: ExerciseData){
        exerciseDao.deleteItem(exerciseData)
    }

    suspend fun deleteAll(){
        exerciseDao.deleteAll()
    }

    fun getAllDataID(accountid: Int): LiveData<List<ExerciseData>> {
       return exerciseDao.getAllDataByID(accountid)
    }

    fun getTask(exerciseId: Int): LiveData<Int> {
        return exerciseDao.getTask(exerciseId)
    }

}