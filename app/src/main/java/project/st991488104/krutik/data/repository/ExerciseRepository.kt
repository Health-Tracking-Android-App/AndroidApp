package project.st991488104.krutik.data.repository

import androidx.lifecycle.LiveData
import project.st991488104.krutik.data.Dao.ExerciseDao
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.models.ToDoData

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

    fun getTask(exerciseId: Int): LiveData<Int> {
        return exerciseDao.getTask(exerciseId)
    }

}