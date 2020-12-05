package project.st991488104.krutik.data.repository

import androidx.lifecycle.LiveData
import project.st991488104.krutik.data.Dao.ToDoDao
import project.st991488104.krutik.data.models.ToDoData


class ToDoRepository(private val toDoDao: ToDoDao) {

    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    fun getAllData(exerciseId: Int): LiveData<List<ToDoData>> {
        return toDoDao.getAllData(exerciseId)
    }

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoData: ToDoData){
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }

}